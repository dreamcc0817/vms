package com.bonc.upms.config;

import com.bonc.common.security.componet.DynamicSecurityService;
import com.bonc.common.security.config.SecurityConfig1;
import com.bonc.upms.service.ISysUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title: vms
 * @Package: com.bonc.upms.config
 * @Description:
 * @Author: dreamcc
 * @Date: 2020/4/20 20:37
 * @Version: V1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UpmsSecurityConfig extends SecurityConfig1 {

	private ISysUserService sysUserService;


	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		//获取登录用户信息
		return username -> sysUserService.loadUserByUsername(username);
	}

	@Bean
	public DynamicSecurityService dynamicSecurityService(){
		return new DynamicSecurityService() {
			Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
			@Override
			public Map<String, ConfigAttribute> loadDataSource() {
				return map;
			}
		};
	}
}
