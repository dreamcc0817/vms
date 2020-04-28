package com.bonc.upms.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: vms
 * @Package: com.bonc.upms.entity
 * @Description: 用户权限关系类
 * @Author: dreamcc
 * @Date: 2020/4/23 22:21
 * @Version: V1.0
 */
@Data
public class SysUserPermissionRelation implements Serializable {
	private Long id;
	/**
	 * 用户ID
	 */
	private Long sysUserId;
	/**
	 * 权限ID
	 */
	private Long permissionId;
	/**
	 *
	 */
	private Integer type;
}
