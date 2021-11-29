package com.dreamcc.iot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Title: vms
 * @Package: com.dreamcc.vms.gateway.util
 * @Description: 常量
 * @Author: dreamcc
 * @Date: 2019/12/11 17:27
 * @Version: V1.0
 */
public class Const {

	/**
	 * 网关常量
	 */
	@Getter
	@AllArgsConstructor
	public enum Gateway{
		/**
		 * 消息头长度
		 */
		RPC_HEART_BEAT(4001,"心跳检测");
		/**
		 * 说明
		 */
		private Integer code;
		/**
		 * 值
		 */
		private String value;
	}

	/**
	 * 消息体解码常量
	 */
	@Getter
	@AllArgsConstructor
	public enum XMLFramework{
		/**
		 * 消息头长度
		 */
		HEAD_LENGTH(28,"消息头长度"),
		/**
		 * 版本信息
		 */
		VERSION(0x0100,"版本"),
		/**
		 * 指令类型 ：二进制
		 */
		CMDTYPE_BINARY(1,"指令类型"),
		/**
		 * 指令类型 ：XML明文
		 */
		CMDTYPE_XML(0,"指令类型");
		/**
		 * 值
		 */
		private Integer code;
		/**
		 * 说明
		 */
		private String value;
	}

	/**
	 * 厂家
	 */
	public static final String XMLFRAMEWORK_COMPANY_TYPE= "BST.";
	/**
	 * 设备在线状态
	 */
	public static final int DEVICE_ONLINE = 1;
}
