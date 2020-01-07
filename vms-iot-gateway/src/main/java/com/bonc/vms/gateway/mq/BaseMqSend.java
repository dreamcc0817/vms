package com.bonc.vms.gateway.mq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: vms
 * @Package: com.bonc.vms.gateway.mq
 * @Description: mq发送消息基类
 * @Author: dreamcc
 * @Date: 2019/12/31 10:48
 * @Version: V1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseMqSend {
	/**
	 * ip地址
	 */
	private String ip;
	/**
	 * 接收到的消息
	 */
	private Object msg;
	/**
	 * 设备状态
	 */
	private Integer state;
}
