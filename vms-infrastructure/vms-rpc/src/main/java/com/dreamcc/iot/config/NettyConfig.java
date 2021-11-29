package com.dreamcc.iot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Title: vms
 * @Package: com.dreamcc.vms.gateway.config
 * @Description: netty网关配置类
 * @Author: dreamcc
 * @Date: 2019/12/12 17:28
 * @Version: V1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyConfig {
	/**
	 * nettty服务端端口号
	 */
	@Value("${netty.port}")
	private int port;
	/**
	 * 用于接收客户端的TCP连接
	 */
	@Value("${netty.bossThreads}")
	private int bossThreads;
	/**
	 * 用于处理I/O相关的读写操作，或者执行系统Task、定时任务Task等
	 */
	@Value("${netty.workerThreads}")
	private int workerThreads;
}
