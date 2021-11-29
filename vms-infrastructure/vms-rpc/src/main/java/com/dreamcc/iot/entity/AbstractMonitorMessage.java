package com.dreamcc.iot.entity;

/**
 * @Title: vms
 * @Package: com.dreamcc.vms.gateway.entity
 * @Description: 监控设备接受消息基类，实现类必须在构造方法中赋予msgType值
 * @Author: dreamcc
 * @Date: 2019/12/11 10:23
 * @Version: V1.0
 */
public abstract class AbstractMonitorMessage {
	/**
	 * 消息类型
	 */
	public Integer msgType;
}
