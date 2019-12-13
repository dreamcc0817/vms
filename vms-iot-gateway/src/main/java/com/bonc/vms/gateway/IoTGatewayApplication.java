package com.bonc.vms.gateway;

import com.bonc.vms.gateway.config.NettyConfig;
import com.bonc.vms.gateway.server.NettyServer;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Title: vms
 * @Package: com.bonc.vms.gateway
 * @Description: 安防系统网关启动类
 * @Author: dreamcc
 * @Date: 2019/12/6 13:43
 * @Version: V1.0
 */
@Slf4j
@SpringBootApplication
public class IoTGatewayApplication implements CommandLineRunner {

	private final NettyServer nettyServer;
	private final NettyConfig nettyConfig;

	@Autowired
	public IoTGatewayApplication(NettyServer nettyServer, NettyConfig nettyConfig) {
		this.nettyServer = nettyServer;
		this.nettyConfig = nettyConfig;
	}

	public static void main(String[] args) {
		SpringApplication.run(IoTGatewayApplication.class,args);
	}

	@Override
	public void run(String... args) throws Exception {
		ChannelFuture future = nettyServer.start();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> nettyServer.destroy()));
		//服务端管道关闭监听器并同步阻塞，直到channel关闭，线程才会向下执行，结束进程
		log.info("【网关】 关闭网关 ......");
		future.channel().closeFuture().syncUninterruptibly();
	}
}
