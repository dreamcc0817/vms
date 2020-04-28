package com.bonc.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.upms.entity.SysPermission;
import com.bonc.upms.mapper.SysPermissionMapper;
import com.bonc.upms.service.ISysPermissionService;
import com.bonc.upms.vo.SysPermissionNode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: vms
 * @Package: com.bonc.upms.service.impl
 * @Description: 权限Service实现类
 * @Author: dreamcc
 * @Date: 2020/4/23 21:07
 * @Version: V1.0
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

	@Override
	public List<SysPermissionNode> treeList() {
		List<SysPermission> permissionList = this.list();
		List<SysPermissionNode> result = permissionList.stream()
				.filter(permission -> permission.getPid().equals(0L))
				.map(permisson -> convert(permisson, permissionList))
				.collect(Collectors.toList());
		return null;
	}

	/**
	 * 权限转换为带有子级的权限对象
	 *
	 * @param sysPermission 根节点权限对象
	 * @param permissionList 权限列表
	 * @return 树形权限对象
	 */
	private SysPermissionNode convert(SysPermission sysPermission, List<SysPermission> permissionList) {
		SysPermissionNode sysPermissionNode = new SysPermissionNode();
		BeanUtils.copyProperties(sysPermission, sysPermissionNode);
		List<SysPermissionNode> children = permissionList.stream()
				.filter(subPermission -> subPermission.getPid().equals(sysPermission.getId()))
				.map(subPermission -> convert(sysPermission,permissionList))
				.collect(Collectors.toList());
		sysPermissionNode.setChildren(children);
		return sysPermissionNode;
	}
}
