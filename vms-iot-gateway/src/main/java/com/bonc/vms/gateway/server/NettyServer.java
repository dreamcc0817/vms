package com.bonc.vms.gateway.server;

import com.bonc.vms.gateway.config.NettyConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
@Component
public class NettyServer {

	/**
	 * Netty配置类
	 */
	private NettyConfig nettyConfig;
	/**
	 * 用于接收客户端的TCP连接
	 */
	private EventLoopGroup bossGroup;
	/**
	 * 用于处理I/O相关的读写操作，或者执行系统Task、定时任务Task等
	 */
	private EventLoopGroup workerGroup;

	ChannelFuture future;

	public NettyServer(NettyConfig nettyConfig) {
		this.nettyConfig = nettyConfig;
	}

	public ChannelFuture start() {
		//创建主线程，用于接收客户端的TCP连接
		bossGroup = new NioEventLoopGroup(nettyConfig.getBossThreads());
		//创建工作线程，用于处理I/O相关的读写操作，或者执行系统Task、定时任务Task等
		workerGroup = new NioEventLoopGroup(nettyConfig.getWorkerThreads());
		//Server端启动器
		ServerBootstrap bootstrap = new ServerBootstrap()
				.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ServerChannelInitializer())
				.localAddress(new InetSocketAddress(nettyConfig.getPort()))
				//打印日志信息
				.handler(new LoggingHandler(LogLevel.INFO))
				//设置队列大小
				.option(ChannelOption.SO_BACKLOG, 1024)
				//两小时没有数据的通信时，TCP会发送一个活动检测的数据报文
				.childOption(ChannelOption.SO_KEEPALIVE, true);

		try {
			future = bootstrap.bind().sync();
			if (future != null && future.isSuccess()) {
				log.info("【网关】 服务器启动成功，开始监听端口: {}", nettyConfig.getPort());
			} else {
				log.error("【网关】 服务器启动失败 ......");
			}
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			log.error("【网关】 服务器启动失败：{}", e.getMessage());
			e.printStackTrace();
		} finally {
			//关闭主线程组
			bossGroup.shutdownGracefully();
			//关闭工作线程组
			workerGroup.shutdownGracefully();
		}
		return future;
	}

	/**
	 * 关闭netty服务器
	 */
	public void destroy() {
		log.info("Shutdown Netty Server...");
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
		log.info("Shutdown Netty Server Success!");
	}
}
