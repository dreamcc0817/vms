package com.dreamcc.service.alarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @Title: vms
 * @Package: com.dreamcc.service.alarm
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/12/27 17:02
 * @Version: V1.0
 */
@EnableBinding({Sink.class})
@SpringBootApplication
public class AlarmApplication {
	public static void main(String[] args) {
		SpringApplication.run(AlarmApplication.class, args);
	}
}
