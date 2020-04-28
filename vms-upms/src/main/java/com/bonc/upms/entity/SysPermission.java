package com.bonc.upms.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台用户权限表(SysPermission)表实体类
 *
 * @author makejava
 * @since 2020-04-23 20:27:45
 */
@Data
public class SysPermission implements Serializable {

	private Long id;
	/**
	 * 父级权限id
	 */
	@ApiModelProperty(value = "父级权限id")
	private Long pid;
	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称")
	private String name;
	/**
	 * 权限值
	 */
	@ApiModelProperty(value = "权限值")
	private String value;
	/**
	 * 图标
	 */
	@ApiModelProperty(value = "图标")
	private String icon;
	/**
	 * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
	 */
	@ApiModelProperty(value = "权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）")
	private Integer type;
	/**
	 * 前端资源路径
	 */
	@ApiModelProperty(value = "前端资源路径")
	private String uri;
	/**
	 * 启用状态；0->禁用；1->启用
	 */
	@ApiModelProperty(value = "启用状态；0->禁用；1->启用")
	private Integer status;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;
}