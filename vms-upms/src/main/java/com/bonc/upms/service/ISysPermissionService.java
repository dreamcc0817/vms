package com.bonc.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bonc.upms.entity.SysPermission;
import com.bonc.upms.vo.SysPermissionNode;

import java.util.List;

/**
 * @Title: vms
 * @Package: com.bonc.upms.service
 * @Description: 权限Service
 * @Author: dreamcc
 * @Date: 2020/4/23 20:53
 * @Version: V1.0
 */
public interface ISysPermissionService extends IService<SysPermission> {
	/**
	 * 以层级结构返回所有权限
	 */
	List<SysPermissionNode> treeList();
}
