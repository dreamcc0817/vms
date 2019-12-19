package com.bonc.service.device.rpc.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.rpc.codec
 * @Description: JSON解码
 * @Author: dreamcc
 * @Date: 2019/12/18 16:16
 * @Version: V1.0
 */

public class JSONDecoder extends LengthFieldBasedFrameDecoder {
	public JSONDecoder() {
		super(65535, 0, 4,0,4);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		ByteBuf decode = (ByteBuf) super.decode(ctx, in);
		if (decode==null){
			return null;
		}
		int dataLen = decode.readableBytes();
		byte[] bytes = new byte[dataLen];
		decode.readBytes(bytes);
		Object parse = JSON.parse(bytes);
		return parse;
	}
}
