package com.bonc.upms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;

/**
 * @Title: vms
 * @Package: com.bonc.upms.dto
 * @Description: 用户登录参数
 * @Author: dreamcc
 * @Date: 2020/4/20 20:04
 * @Version: V1.0
 */
@Data
public class LoginParamDTO {
	@ApiModelProperty(value = "用户名", required = true)
	@NotEmpty(message = "用户名不能为空")
	private String username;
	@ApiModelProperty(value = "密码", required = true)
	@NotEmpty(message = "密码不能为空")
	private String password;
}
