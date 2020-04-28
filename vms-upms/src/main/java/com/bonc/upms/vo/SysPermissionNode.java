package com.bonc.upms.vo;

import com.bonc.upms.entity.SysPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Title: vms
 * @Package: com.bonc.upms.vo
 * @Description: 后台权限节点封装
 * @Author: dreamcc
 * @Date: 2020/4/23 21:02
 * @Version: V1.0
 */
public class SysPermissionNode extends SysPermission {
	/**
	 * 子节点
	 */
	@Getter
	@Setter
	private List<SysPermissionNode> children;
}
