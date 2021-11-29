package com.dreamcc.service.device.rpc.codec;


import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @Title: vms
 * @Package: com.dreamcc.service.device.rpc.codec
 * @Description: JSON编码
 * @Author: dreamcc
 * @Date: 2019/12/18 8:59
 * @Version: V1.0
 */
public class JSONEncoder extends MessageToMessageEncoder {
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, List out) throws Exception {
		ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
		byte[] bytes = JSON.toJSONBytes(msg);
		byteBuf.writeInt(bytes.length);
		byteBuf.writeBytes(bytes);
		out.add(byteBuf);
	}
}
