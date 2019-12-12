package com.bonc.vms.gateway;

import com.bonc.vms.gateway.server.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

/**
 * @Title: vms
 * @Package: com.bonc.vms.gateway
 * @Description: 网关启动类
 * @Author: dreamcc
 * @Date: 2019/12/6 13:43
 * @Version: V1.0
 */
@SpringBootApplication
public class IoTGateway {
	public static void main(String[] args) {
		SpringApplication.run(IoTGateway.class);
		//启动服务端
		NettyServer nettyServer = new NettyServer();
		nettyServer.start(new InetSocketAddress("127.0.0.1", 9999));
	}
}
