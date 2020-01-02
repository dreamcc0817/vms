package com.bonc.service.alarm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@Service
public class OrderlyReceiveService {

	private static final Logger log = LoggerFactory
			.getLogger(OrderlyReceiveService.class);

	private AtomicInteger count = new AtomicInteger(1);

	//@StreamListener(Sink.INPUT)
	public void receiveOrderlyMsg(String receiveMsg) {
		if (count.getAndIncrement() <= 5) {
            throw new RuntimeException("Oops: " + receiveMsg);
		}
		else {
			log.info("receiveOrderlyMsg: " + receiveMsg);
		}
	}

}