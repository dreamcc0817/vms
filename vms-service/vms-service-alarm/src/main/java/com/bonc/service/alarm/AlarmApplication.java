package com.bonc.service.alarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;

/**
 * @Title: vms
 * @Package: com.bonc.service.alarm
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/12/27 17:02
 * @Version: V1.0
 */
@EnableBinding({ Source.class})
@SpringBootApplication
public class AlarmApplication {
	public static void main(String[] args) {
		SpringApplication.run(AlarmApplication.class, args);
	}



	@Bean
	public CustomRunner customRunner() {
		return new CustomRunner();
	}

	public static class CustomRunner implements CommandLineRunner {

		@Autowired
		private Source source;

		@Override
		public void run(String... args) throws Exception {
			int count = 100;
			for (int index = 1; index <= count; index++) {
				source.output().send(MessageBuilder.withPayload("msg-" + index)
						.setHeader("index", index).build());
			}
		}
	}
}
