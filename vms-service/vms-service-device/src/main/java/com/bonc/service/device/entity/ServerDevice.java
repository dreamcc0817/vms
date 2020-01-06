package com.bonc.service.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.entity
 * @Description: 服务器设备
 * @Author: dreamcc
 * @Date: 2019/12/31 15:41
 * @Version: V1.0
 */
@ApiModel(description = "服务器信息")
@Data
@TableName("device_server")
public class ServerDevice implements Serializable {

	private static final long serialVersionUID = 4347865522948335491L;

	/**
	 * 主键
	 */
	@ApiModelProperty(hidden = true)
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;
	/**
	 * 服务器名称
	 */
	@ApiModelProperty(value = "服务器名称", example = "xxx的window 10")
	private String serverName;
	/**
	 * 设备编码，国标设备级联使用
	 */
	@ApiModelProperty(value = "设备编码", example = "1000245124651", dataType = "Long", required = true)
	private Long deviceCode;
	/**
	 * 设备IP地址
	 */
	@ApiModelProperty(value = "IP地址", example = "192.168.1.1", required = true)
	private String host;
	/**
	 * 服务器账户信息
	 */
	@ApiModelProperty(value = "账户信息", example = "admin")
	private String deviceAccout;
	/**
	 * 服务器密码
	 */
	@ApiModelProperty(value = "账户密码", example = "password")
	private String devicePwd;
	/**
	 * 状态
	 */
	@ApiModelProperty(hidden = true)
	private Integer state;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注", example = "这是一条神奇的备注")
	private String note;
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
	/**
	 * 是否失效
	 */
	@ApiModelProperty(hidden = true)
	@TableLogic
	private Integer delFlag;
}
