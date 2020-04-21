package com.bonc.upms.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.common.security.util.JwtTokenUtil;
import com.bonc.upms.dto.SysUserDetailsDTO;
import com.bonc.upms.entity.SysUser;
import com.bonc.upms.mapper.SysUserMapper;
import com.bonc.upms.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Title: vms
 * @Package: com.bonc.upms.service.impl
 * @Description: 用户表 服务实现类
 * @Author: dreamcc
 * @Date: 2020/1/4 19:15
 * @Version: V1.0
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * 登录功能
	 *
	 * @param username 用户名
	 * @param password 密码
	 * @return token
	 */
	@Override
	public String login(String username, String password) {
		String token = null;
		try {
			UserDetails userDetails = loadUserByUsername(username);
			if(!new BCryptPasswordEncoder().matches(password,userDetails.getPassword())){
				throw new BadCredentialsException("密码不正确");
			}
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			token = jwtTokenUtil.generateToken(userDetails);
		} catch (BadCredentialsException e) {
			log.warn("登录异常：{}",e.getMessage());
		}
		return token;
	}

	/**
	 * 获取用户信息
	 *
	 * @param username 用户名
	 * @return 用户信息
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		SysUser sysUser = this.getOne(Wrappers.<SysUser>query().lambda()
			.eq(SysUser::getUsername,username));
		return new SysUserDetailsDTO(sysUser);
	}
}
