package com.bonc.vms.gateway.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Title: vms
 * @Package: com.bonc.vms.gateway.mq
 * @Description: vms自定义生产者
 * @Author: dreamcc
 * @Date: 2020/1/7 16:23
 * @Version: V1.0
 */
public interface VMSSource {

	/**
	 * vms-output
	 */
	String VMS_OUTPUT = "vms-output";

	/**
	 * 输出
	 *
	 * @return MessageChannel
	 */
	@Output
	MessageChannel output();
}
