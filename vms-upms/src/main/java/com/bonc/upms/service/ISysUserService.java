package com.bonc.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bonc.upms.entity.SysResource;
import com.bonc.upms.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @Title: vms
 * @Package: com.bonc.upms.service
 * @Description: 用户表 服务类
 * @Author: dreamcc
 * @Date: 2020/1/4 19:02
 * @Version: V1.0
 */
public interface ISysUserService extends IService<SysUser> {
	/**
	 * 登录功能
	 *
	 * @param username 用户名
	 * @param password 密码
	 * @return 生成的JWT的token
	 */
	String login(String username, String password);

	/**
	 * 根据用户名获取用户信息
	 *
	 * @param username 用户名
	 * @return 用户信息
	 */
	UserDetails loadUserByUsername(String username);

	/**
	 * 获取指定用户的可访问资源
	 * @param userId 用户ID
	 * @return 用户可访问资源
	 */
	List<SysResource> getResourceList(Long userId);

}
