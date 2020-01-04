package com.bonc.service.alarm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.service.impl
 * @Description:
 * @Author: dreamcc
 * @Date: 2020/1/4 9:51
 * @Version: V1.0
 */
@Service
@Slf4j
public class TestService {
	@StreamListener(Sink.INPUT)
	public void checkDeviceState(String message) {
		log.info("收到mq消息{}", message);
	}
}
