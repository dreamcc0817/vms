package com.bonc.vms.gateway.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @Title: vms
 * @Package: com.bonc.vms.gateway.server
 * @Description: Netty服务启动监听器
 * @Author: dreamcc
 * @Date: 2019/12/6 13:50
 * @Version: V1.0
 */
@Slf4j
public class NettyServer {
	public void start(InetSocketAddress socketAddress) {
		//TODO 将固定值变为可配置
		//创建主线程
		EventLoopGroup bossGroup = new NioEventLoopGroup(2);
		//创建工作线程
		EventLoopGroup workGroup = new NioEventLoopGroup(200);

		ServerBootstrap bootstrap = new ServerBootstrap()
				.group(bossGroup,workGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ServerChannelInitializer())
				.localAddress(socketAddress)
				//设置队列大小
				.option(ChannelOption.SO_BACKLOG,1024)
				//两小时没有数据的通信时，TCP会发送一个活动检测的数据报文
				.childOption(ChannelOption.SO_KEEPALIVE,true);

		try {
			ChannelFuture future = bootstrap.bind(socketAddress).sync();
			log.info("【网关】 服务器启动开始监听端口: {}", socketAddress.getPort());
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			//关闭主线程组
			bossGroup.shutdownGracefully();
			//关闭工作线程组
			workGroup.shutdownGracefully();
		}
	}
}
