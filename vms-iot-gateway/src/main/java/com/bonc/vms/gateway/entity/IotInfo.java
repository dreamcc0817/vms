package com.bonc.vms.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Title: vms
 * @Package: com.bonc.vms.gateway.entity
 * @Description: IOT设备信息
 * @Author: dreamcc
 * @Date: 2020/1/5 19:14
 * @Version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IotInfo {
	/**
	 * IOT设备ID
	 */
	private String id;
	/**
	 * 设备消息
	 */
	private Map<String, String> data;
}
