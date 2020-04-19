package com.bonc.upms.service;

import com.bonc.upms.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
