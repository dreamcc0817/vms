package com.dreamcc.service.device.dto;

import com.dreamcc.service.device.entity.ServerDevice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Title: vms
 * @Package: com.dreamcc.service.device.dto
 * @Description: 服务器设备与服务单元DTO
 * @Author: dreamcc
 * @Date: 2020/1/2 10:41
 * @Version: V1.0
 */
@ApiModel(description = "服务器与服务单元信息")
@Data
@EqualsAndHashCode(callSuper = true)
public class ServerDeviceDTO extends ServerDevice {
	private static final long serialVersionUID = -8698067106234583032L;

	/**
	 * 服务单元ID
	 */
	@ApiModelProperty(value = "服务单元类型",example = "10002")
	private List<Integer> serverUnitType;
}
