package com.dreamcc.service.device.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Title: vms
 * @Package: com.dreamcc.service.device.entity
 * @Description: 服务器设备与服务单元关系类
 * @Author: dreamcc
 * @Date: 2020/1/2 11:25
 * @Version: V1.0
 */
@Data
@TableName("device_server_unit_ref")
public class ServerUnitRef {

	/**
	 * 服务器ID
	 */
	private Long serverId;
	/**
	 * 设备服务单元ID
	 */
	private Long unitId;
}
