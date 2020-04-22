package com.bonc.upms.service.impl;

import com.bonc.upms.entity.SysResource;
import com.bonc.upms.entity.SysUser;
import com.bonc.upms.service.IRedisService;
import com.bonc.upms.service.ISysUserCacheService;
import com.bonc.upms.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: vms
 * @Package: com.bonc.upms.service.impl
 * @Description: ISysUserCacheService实现类
 * @Author: dreamcc
 * @Date: 2020/4/22 20:21
 * @Version: V1.0
 */
@Service
public class SysUserCacheImpl implements ISysUserCacheService {

	@Value("${redis.database}")
	private String REDIS_DATABASE;
	@Value("${redis.expire.common}")
	private Long REDIS_EXPIRE;
	@Value("${redis.key.admin}")
	private String REDIS_KEY_ADMIN;
	@Value("${redis.key.resourceList}")
	private String REDIS_KEY_RESOURCE_LIST;

	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private IRedisService redisService;

	/**
	 * 删除用户缓存
	 *
	 * @param userId 用户ID
	 */
	@Override
	public void delSysUser(Long userId) {

	}

	@Override
	public void delResourceList(Long userId) {

	}

	@Override
	public void delResourceListByRole(Long roleId) {

	}

	@Override
	public void delResourceListByRoleIds(List<Long> roleIds) {

	}

	@Override
	public void delResourceListByResource(Long resourceId) {

	}

	@Override
	public SysUser getSysUser(String username) {
		return null;
	}

	/**
	 * 设置用户信息缓存
	 *
	 * @param sysUser 用户信息
	 */
	@Override
	public void setSysUser(SysUser sysUser) {
		String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + sysUser.getUsername();
		redisService.set(key,sysUser,REDIS_EXPIRE);
	}

	@Override
	public List<SysResource> getResourceList(Long userId) {
		return null;
	}

	@Override
	public void setResourceList(Long adminId, List<SysResource> resourceList) {

	}
}
