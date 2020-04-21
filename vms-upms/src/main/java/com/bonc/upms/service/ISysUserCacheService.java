package com.bonc.upms.service;

import com.bonc.upms.entity.SysResource;
import com.bonc.upms.entity.SysUser;

import java.util.List;

/**
 * @Title: vms
 * @Package: com.bonc.upms.service
 * @Description: 后台用户缓存操作类
 * @Author: dreamcc
 * @Date: 2020/4/21 22:23
 * @Version: V1.0
 */
public interface ISysUserCacheService {
	/**
	 * 删除用户缓存
	 *
	 * @param userId 用户ID
	 */
	void delSysUser(Long userId);

	/**
	 * 删除用户资源列表缓存
	 *
	 * @param userId 用户ID
	 */
	void delResourceList(Long userId);

	/**
	 * 当角色相关资源信息改变删除相关用户缓存
	 *
	 * @param roleId 角色ID
	 */
	void delResourceListByRole(Long roleId);

	/**
	 * 当角色相关资源信息改变时删除相关后台用户缓存
	 *
	 * @param roleIds 角色ID列表
	 */
	void delResourceListByRoleIds(List<Long> roleIds);

	/**
	 * 当资源信息改变时，删除资源项目后台用户缓存
	 *
	 * @param resourceId 资源ID
	 */
	void delResourceListByResource(Long resourceId);

	/**
	 * 根据用户名获取用户信息
	 *
	 * @param username 用户名
	 * @return 用户信息
	 */
	SysUser getSysUser(String username);

	/**
	 * 设置用户缓存
	 *
	 * @param sysUser 用户信息
	 */
	void setSysUser(SysUser sysUser);

	/**
	 * 获取缓存用户资源列表
	 *
	 * @param userId 用户ID
	 * @return 用户资源列表
	 */
	List<SysResource> getResourceList(Long userId);

	/**
	 * 设置用户资源列表
	 *
	 * @param adminId      用户ID
	 * @param resourceList 资源列表
	 */
	void setResourceList(Long adminId, List<SysResource> resourceList);
}
