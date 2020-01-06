package com.bonc.service.device.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.UtilityClass;

import java.util.Arrays;

/**
 * @Title: vms
 * @Package: com.bonc.service.device
 * @Description: 设备资源常量类
 * @Author: dreamcc
 * @Date: 2020/1/3 10:45
 * @Version: V1.0
 */
@UtilityClass
public class DeviceConsts {

	/**
	 * 无效端口
	 */
	public static final Integer INVALID_PORT = 0;
	/**
	 * 默认负载数量
	 */
	public static final Integer LOAD_DUFAULT = 50;
	/**
	 * 在线状态
	 */
	public static final Integer ON_LINE = 1;
	/**
	 * 离线状态
	 */
	public static final Integer OFF_ONLINE = 0;

	/**
	 * 服务单元类型
	 */
	@Getter
	@AllArgsConstructor
	public enum UnitType {
		/**
		 * 客户端
		 */
		CU(1001, "客户端"),
		/**
		 * 设备接入
		 */
		AU(1002, "设备接入"),
		/**
		 * 人脸分析
		 */
		IVAS(1003, "人脸分析服务"),
		/**
		 * 服务存储
		 */
		MU(1016, "服务存储单元");


		/**
		 * 值
		 */
		private Integer value;
		/**
		 * 说明
		 */
		private String name;

		/**
		 * 根据编码查找名称
		 *
		 * @param value 编码
		 * @return 名称
		 */
		public static String getName(Integer value) {
			return Arrays
					.stream(UnitType.values())
					.filter(unitType -> unitType.getValue().equals(value))
					.findFirst()
					.map(UnitType::getName)
					.orElse(null);
		}
	}
}
