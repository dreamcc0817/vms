package com.bonc.upms.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.common.core.constant.CommonConsts;
import com.bonc.common.security.util.JwtTokenUtil;
import com.bonc.upms.dto.SysUserDTO;
import com.bonc.upms.dto.SysUserDetailsDTO;
import com.bonc.upms.entity.SysResource;
import com.bonc.upms.entity.SysUser;
import com.bonc.upms.mapper.SysUserMapper;
import com.bonc.upms.service.ISysUserCacheService;
import com.bonc.upms.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
	@Autowired
	private ISysUserCacheService sysUserCacheService;
	@Autowired
	private PasswordEncoder passwordEncoder;

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
			if (!new BCryptPasswordEncoder().matches(password, userDetails.getPassword())) {
				throw new BadCredentialsException("密码不正确");
			}
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			token = jwtTokenUtil.generateToken(userDetails);
		} catch (BadCredentialsException e) {
			log.warn("登录异常：{}", e.getMessage());
		}
		return token;
	}

	/**
	 * 刷新token
	 *
	 * @param oldToken 旧token
	 * @return token
	 */
	@Override
	public String refreshToken(String oldToken) {
		return jwtTokenUtil.refreshToken(oldToken);
	}

	/**
	 * 添加登录记录
	 *
	 * @param username 用户名
	 */
	private void insertLoginLog(String username) {

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
				.eq(SysUser::getUsername, username));
		if (sysUser != null) {
			List<SysResource> sysResources = getResourceList(sysUser.getUserId());
			return new SysUserDetailsDTO(sysUser, sysResources);
		}
		throw new UsernameNotFoundException("用户名或密码错误");
	}

	/**
	 * 根据用户名获取用户信息
	 *
	 * @param username 用户名
	 * @return 用户信息
	 */
	@Override
	public SysUser getSysUserByUsername(String username) {
		SysUser sysUser = sysUserCacheService.getSysUser(username);
		if (sysUser != null) {
			return sysUser;
		}
		sysUser = this.getOne(Wrappers.<SysUser>query().lambda()
				.eq(SysUser::getUsername, username));
		return sysUser;
	}

	/**
	 * 获取指定用户的可访问资源
	 *
	 * @param userId 用户ID
	 * @return 用户可访问资源
	 */
	@Override
	public List<SysResource> getResourceList(Long userId) {
		return null;
	}

	/**
	 * 注册用户信息
	 *
	 * @param userDTO 用户信息
	 * @return 用户信息
	 */
	@Override
	public SysUser register(SysUserDTO userDTO) {
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(userDTO,sysUser);
		sysUser.setCreateTime(LocalDateTime.now());
		sysUser.setLockFlag(CommonConsts.STATUS_NORMAL);
		SysUser repeatUser = this.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, userDTO.getUsername()));
		if (repeatUser != null){
			return null;
		}
		String encodePassword = passwordEncoder.encode(userDTO.getPassword());
		sysUser.setPassword(encodePassword);
		boolean save = this.save(sysUser);
		if(save){
			return sysUser;
		}
		return null;
	}
}
