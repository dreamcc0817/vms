package com.bonc.vms.gateway.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Title: vms
 * @Package: com.bonc.vms.gateway.util
 * @Description: 操作静态常量
 * @Author: dreamcc
 * @Date: 2019/12/13 11:30
 * @Version: V1.0
 */
public class OperConst {
	/**
	 * 设备操作
	 */
	@Getter
	@AllArgsConstructor
	public enum DeviceOper {
		/**
		 * 设备注册
		 */
		REGISTER(1500, "设备注册"),
		/**
		 * Ping任务
		 */
		PING(10025, "设备Ping任务初始化化失败");
		/**
		 * 操作代码
		 */
		private Integer code;
		/**
		 * 操作消息
		 */
		private String msg;
	}

	/**
	 * 编解码操作
	 */
	@Getter
	@AllArgsConstructor
	public enum EnDeCoder {
		/**
		 * 解析XML异常
		 */
		DECODER_XML_ERROR(10020, "解析XML异常");
		/**
		 * 操作代码
		 */
		private Integer code;
		/**
		 * 操作消息
		 */
		private String msg;
	}
}
