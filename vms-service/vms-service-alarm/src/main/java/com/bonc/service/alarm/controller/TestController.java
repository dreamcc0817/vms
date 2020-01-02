package com.bonc.service.alarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Title: vms
 * @Package: com.bonc.service.alarm.controller
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/12/27 17:08
 * @Version: V1.0
 */
@Controller
public class TestController {

	@Autowired
	private Source source;

	@GetMapping("/test")
	public void test(){
		source.output().send(MessageBuilder.withPayload("aaaaa").build());
	}

}
