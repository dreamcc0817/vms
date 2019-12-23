package com.bonc.service.device.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.entity
 * @Description: 服务器设备信息
 * @Author: dreamcc
 * @Date: 2019/12/23 10:22
 * @Version: V1.0
 */
@Data
@Document
public class ServerDevice implements Serializable {

	private static final long serialVersionUID = -312426907893717810L;

	/**
	 * 文档编号，主键
	 */
	@Id
	private Long id;
	/**
	 * ip地址
	 */
	@Field("ip_address")
	private String ip;
	/**
	 * 设备类型
	 */
	@Field("device_type")
	private Integer deviceType;
}
