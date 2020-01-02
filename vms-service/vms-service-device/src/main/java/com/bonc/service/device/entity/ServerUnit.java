package com.bonc.service.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.entity
 * @Description: 设备服务单元
 * @Author: dreamcc
 * @Date: 2019/12/31 17:15
 * @Version: V1.0
 */
@ApiModel(description = "服务器单元信息")
@Data
@TableName("device_unit")
public class ServerUnit implements Serializable {

	private static final long serialVersionUID = 7169840166714296960L;

	/**
	 * 主键ID
	 */
	@ApiModelProperty(hidden = true)
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;
	/**
	 * 服务单元名称
	 */
	@ApiModelProperty(value = "服务单元名称", example = "设备管理服务单元")
	private String unitName;
	/**
	 * 端口号
	 */
	@ApiModelProperty(value = "服务单元端口号", example = "45872")
	private Integer port;
	/**
	 * 服务单元类型
	 */
	@ApiModelProperty(hidden = true)
	private Integer type;
	/**
	 * 负载数量
	 */
	@ApiModelProperty(value = "负载数量", example = "200")
	private Integer loadNum;
	/**
	 * 服务单元状态
	 */
	@ApiModelProperty(hidden = true)
	private Integer state;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(hidden = true)
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(hidden = true)
	private LocalDateTime updateTime;
}
