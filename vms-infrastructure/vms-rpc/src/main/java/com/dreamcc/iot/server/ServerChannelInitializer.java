package com.dreamcc.iot.server;

import com.dreamcc.iot.handler.NettyServerHandler;
import com.dreamcc.iot.codec.AbstractMonitorDecoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @Title: vms
 * @Package: com.dreamcc.vms.gateway.server
 * @Description: netty服务初始化器
 * @Author: dreamcc
 * @Date: 2019/12/6 13:48
 * @Version: V1.0
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
	/**
	 * 初始化
	 *
	 * @param ch 操作的channel
	 */
	@Override
	protected void initChannel(SocketChannel ch) {
		//添加编解码
		ch.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
		ch.pipeline().addLast("decoder", new AbstractMonitorDecoder());
		ch.pipeline().addLast(new NettyServerHandler());
	}
}
