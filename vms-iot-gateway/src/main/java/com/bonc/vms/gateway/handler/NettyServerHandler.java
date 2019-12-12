package com.bonc.vms.gateway.handler;

import com.bonc.vms.gateway.cache.IoTDeviceCache;
import com.bonc.vms.gateway.util.IoTStringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;


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
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


	/**
	 * 客户端连接时触发
	 *
	 * @param ctx ChannelHandlerContext
	 * @throws Exception 连接时发生异常
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		log.info("【网关】 网关收到注册信息Channel active ......");
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
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		log.info("【网关】 读取消息完毕 ......");
	}

	/**
	 * 发生异常时触发
	 *
	 * @param ctx ChannelHandlerContext
	 * @param cause 异常原因
	 * @throws Exception NettyServerHandler异常捕获
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		log.error("【网关】 发生异常：{}",cause.getMessage());
		cause.printStackTrace();
		ctx.close();
	}
}
