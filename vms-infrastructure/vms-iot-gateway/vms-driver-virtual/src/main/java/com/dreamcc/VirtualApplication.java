package com.dreamcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author cloud-cc
 * @ClassName VirtualApplication
 * @Description 虚拟驱动启动类
 * @date 2021/12/6 15:39
 * @Version 1.0
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class VirtualApplication {
    public static void main(String[] args) {
        SpringApplication.run(VirtualApplication.class);
    }
}
