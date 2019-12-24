package com.bonc.vms.gateway.codec;

import cn.hutool.core.util.XmlUtil;
import com.bonc.vms.gateway.entity.AbstractMonitorMessageReceived;
import com.bonc.vms.gateway.entity.HeaderModel;
import com.bonc.vms.gateway.util.Const;
import com.bonc.vms.gateway.util.OperConst;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @Title: vms
 * @Package: com.bonc.vms.gateway.codec
 * @Description: 安防系统解码器抽象类
 * @Author: dreamcc
 * @Date: 2019/12/11 10:05
 * @Version: V1.0
 */
@Slf4j
public abstract class AbstractMonitorDecoder<T extends AbstractMonitorMessageReceived> extends ByteToMessageDecoder {

	/**
	 * 消息类型
	 */
	private int msgType;

	public AbstractMonitorDecoder(){
		//使用模板设计模式，将不同消息体进行不同解码
		//根据反射获取泛型类型
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class<T> entity = (Class<T>) type.getActualTypeArguments()[0];
		try {
			this.msgType = entity.newInstance().msgType;
		} catch (InstantiationException e) {
			log.error("解码发生异常：{}",e.getMessage());
		} catch (IllegalAccessException e) {
			log.error("解码发生异常：{}",e.getMessage());
		}
	}

	/**
	 * 解码
	 *
	 * @param ctx
	 * @param in
	 * @param out
	 * @throws Exception
	 */
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

		//HEAD_LENGTH是我们用于表示头长度的字节
		if (in.readableBytes() < Const.XMLFramework.HEAD_LENGTH.getValue()) {
			// 数据长度小于头，继续收数据
			return;
		}
		//验证消息头
		HeaderModel headerModel = decoderHeader(in);
		//验证报文版本
		if (headerModel.getVersion() != Const.XMLFramework.VERSION.getValue()) {
			log.error("【网关】 报文版本与服务器不一致......");
			ctx.close();
		}

		//标记当前readIndex的位置
		in.markReaderIndex();
		//读取传送过来的消息的长度，返回当前readerIndex的int值，并将readerIndex增加4
		int dataLength = headerModel.getBodyLength();
		// 我们读到的消息体长度为0，这是不应该出现的情况，这里出现这情况，关闭连接。
		if (dataLength < 0) {
			ctx.close();
		}
		//读到的消息体长度如果小于我们传送过来的消息长度，则resetReaderIndex. 这个配合markReaderIndex使用的。把readIndex重置到mark的地方
		if (in.readableBytes() < dataLength) {
			in.resetReaderIndex();
			return;
		}
		//将数据取出
		byte[] body = new byte[dataLength];
		in.readBytes(body);
		String xmlStr = new String(body);
		Map map = XmlUtil.xmlToMap(xmlStr);
		T packet = decodeBody(ctx, map, xmlStr);
		if(packet == null){
			log.info("【网关】发生异常{},{}", OperConst.EnDeCoder.DECODER_XML_ERROR.getCode(),OperConst.EnDeCoder.DECODER_XML_ERROR.getMsg());
			ctx.close();
		}
		packet.setCtx(ctx);
		out.add(packet);
	}

	/**
	 * 解码消息体
	 *
	 * @param ctx
	 * @param map
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	protected abstract T decodeBody(ChannelHandlerContext ctx, Map map, String xml) throws Exception;

	/**
	 * 解码消息头
	 *
	 * @param in
	 * @return
	 */
	private HeaderModel decoderHeader(ByteBuf in) {
		HeaderModel headerModel = new HeaderModel();
		//设置版本号
		headerModel.setVersion(in.readInt());
		//设置公司信息
		byte[] companyByte = new byte[3];
		companyByte[0] = in.readByte();
		companyByte[1] = in.readByte();
		companyByte[2] = in.readByte();
		in.readByte();
		String company = new String(companyByte, CharsetUtil.UTF_8);
		headerModel.setCompany(company);
		//设置消息编号
		headerModel.setMsgType(in.readInt());
		//设置消息类型
		headerModel.setDataType(in.readInt());
		//获取body长度
		headerModel.setBodyLength(in.readInt());
		//获取保留字段
		headerModel.setReserved1(in.readInt());
		//获取保留字段
		headerModel.setReserved2(in.readInt());
		return headerModel;
	}
}
