package com.bonc.vms.gateway.handler;

import com.bonc.vms.gateway.cache.IoTDeviceCache;
import com.bonc.vms.gateway.entity.GlobalInfo;
import com.bonc.vms.gateway.entity.RTUChannelInfo;
import com.bonc.vms.gateway.mq.BaseMqSend;
import com.bonc.vms.gateway.util.Const;
import com.bonc.vms.gateway.util.IoTStringUtil;
import com.bonc.vms.gateway.util.OperConst;
import io.netty.channel.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;


/**
 * @Title: vms
 * @Package: com.bonc.vms.gateway
 * @Description: netty服务端处理器
 * @Author: dreamcc
 * @Date: 2019/12/6 11:23
 * @Version: V1.0
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
@ChannelHandler.Sharable
@NoArgsConstructor
@Component
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


	@Autowired
	private Source source;

	private static NettyServerHandler nettyServerHandler;

	@PostConstruct
	public void init() {
		nettyServerHandler = this;
	}

	/**
	 * 通道注册
	 *
	 * @param ctx ctx
	 * @throws Exception Exception
	 */
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		log.info("【网关】 网关收到注册信息Channel active {}", ctx.channel());
		ChannelId channelId = ctx.channel().id();
		RTUChannelInfo channelInfo = GlobalInfo.CHANNEL_INFO_MAP.getOrDefault(channelId, RTUChannelInfo.builder().sn("unknowSN").channelId(channelId).build());
		GlobalInfo.CHANNEL_INFO_MAP.put(channelId, channelInfo);
		ctx.fireChannelRegistered();
	}

	/**
	 * 客户端连接时触发
	 *
	 * @param ctx ChannelHandlerContext
	 * @throws Exception 连接时发生异常
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		//获取Channel信息
		Channel channel = ctx.channel();
		//获取IP地址
		InetSocketAddress inetSocketAddress = (InetSocketAddress) channel.remoteAddress();
		//格式化IP地址
		String ipAddress = IoTStringUtil.formatIpAddress(inetSocketAddress.getHostString(), String.valueOf(inetSocketAddress.getPort()));
		//保存连接信息到缓存中
		IoTDeviceCache.set(ipAddress, channel);
	}

	/**
	 * 接受消息触发
	 *
	 * @param ctx ChannelHandlerContext
	 * @param msg 接受到的消息
	 * @throws Exception 接收消息发生的异常
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		log.info("【网关】 收到消息：{}", msg.toString());
		InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
		//格式化IP地址
		String ipAddress = IoTStringUtil.formatIpAddress(inetSocketAddress.getHostString(), String.valueOf(inetSocketAddress.getPort()));
		BaseMqSend baseMqSend = BaseMqSend.builder().ip(ipAddress).msg(msg).state(Const.DEVICE_ONLINE).build();
		Message<BaseMqSend> build = MessageBuilder.withPayload(baseMqSend).build();
		Map<String, String> msg1 = (HashMap) msg;
		//注册时发送消息至MQ
		if (msg1.get(OperConst.CMD_TYPE) != null && msg1.get(OperConst.CMD_TYPE).equals(OperConst.DeviceOper.REGISTER.getValue())) {
			nettyServerHandler.source.output().send(build);
			log.info("【MQ】 发生消息{}完毕 ......", build);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		log.info("【网关】 读取消息完毕 ......");

	}

	/**
	 * 发生异常时触发
	 *
	 * @param ctx   ChannelHandlerContext
	 * @param cause 异常原因
	 * @throws Exception NettyServerHandler异常捕获
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		log.error("【网关】 发生异常：{}", cause.getMessage());
		cause.printStackTrace();
		ctx.close();
	}
}
