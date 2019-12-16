package com.bonc.vms.gateway.entity;

import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.Setter;

/**
 * @Title: vms
 * @Package: com.bonc.vms.gateway.entity
 * @Description: 接受消息的基类
 * @Author: dreamcc
 * @Date: 2019/12/11 11:20
 * @Version: V1.0
 */
@Getter
@Setter
public abstract class AbstractMonitorMessageReceived extends AbstractMonitorMessage {

	private ChannelHandlerContext ctx;

	/**
	 * 处理解码逻辑实现类
	 */
	public abstract void process();
}
