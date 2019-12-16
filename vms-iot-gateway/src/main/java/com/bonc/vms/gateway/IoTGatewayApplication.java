package com.bonc.vms.gateway;

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
@SpringBootApplication
public class IoTGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(IoTGatewayApplication.class,args);
	}
}
