package com.dreamcc.iot.entity;

import lombok.Data;

/**
 * @Title: vms
 * @Package: com.dreamcc.vms.gateway.entity
 * @Description: 消息头封装类
 * @Author: dreamcc
 * @Date: 2019/12/12 11:15
 * @Version: V1.0
 */
@Data
public class HeaderModel {
	/**
	 * 版本信息
	 */
	private int version;
	/**
	 * 公司信息
	 */
	private String company;
	/**
	 * 消息类型
	 */
	private int msgType;
	/**
	 * 消息数据类型：二进制，xml
	 */
	private int dataType;
	/**
	 * 消息体长度
	 */
	private int bodyLength;
	/**
	 * 保留字段一
	 */
	private int reserved1;
	/**
	 * 保留字段二
	 */
	private int reserved2;
}
