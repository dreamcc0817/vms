package com.bonc.vms.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Title: vms
 * @Package: com.bonc.vms.nacos
 * @Description: 消费类
 * @Author: dreamcc
 * @Date: 2019/12/2 19:50
 * @Version: V1.0
 */
@SpringBootApplication
public class ProviderApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ProviderApplication.class, args);
		String userName = applicationContext.getEnvironment().getProperty("user.name");
		String userAge = applicationContext.getEnvironment().getProperty("user.age");
		System.err.println("user name :"+userName+"; age: "+userAge);
	}
}
