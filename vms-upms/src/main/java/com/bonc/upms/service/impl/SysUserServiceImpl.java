package com.bonc.upms.service.impl;

import com.bonc.upms.entity.SysUser;
import com.bonc.upms.mapper.SysUserMapper;
import com.bonc.upms.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Title: vms
 * @Package: com.bonc.upms.service.impl
 * @Description: 用户表 服务实现类
 * @Author: dreamcc
 * @Date: 2020/1/4 19:15
 * @Version: V1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

	@Override
	public String login(String username, String password) {
		return null;
	}
}
