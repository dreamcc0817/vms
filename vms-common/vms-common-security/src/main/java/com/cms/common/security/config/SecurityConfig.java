package com.cms.common.security.config;

import com.cms.common.security.componet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Title: vms
 * @Package: com.cms.common.security.config
 * @Description: 对SpringSecurity的配置的扩展，支持自定义白名单资源路径和查询用户逻辑
 * @Author: dreamcc
 * @Date: 2020/4/19 17:26
 * @Version: V1.0
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private DynamicSecurityService dynamicSecurityService;

	@Autowired
	private IgnoreUrlsConfig ignoreUrlsConfig;
	@Autowired
	private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	@Autowired
	private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
	@Autowired
	private DynamicSecurityFilter dynamicSecurityFilter;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.
				authorizeRequests();
		//不需要保护的资源路径运行访问
		for (String url : ignoreUrlsConfig.getUrls()) {
			registry.antMatchers(url).permitAll();
		}
		//允许跨域请求的OPTIONS请求
		registry.antMatchers(HttpMethod.OPTIONS).permitAll();
		registry.and()
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				//关闭跨站请求防护及不使用session
				.and()
				.csrf()
				.disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				//自定义权限拒绝类处理
				.and()
				.exceptionHandling()
				.accessDeniedHandler(restfulAccessDeniedHandler)
				.authenticationEntryPoint(restAuthenticationEntryPoint)
				// 自定义权限拦截器JWT过滤器
				.and()
				.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
		//有动态权限配置时添加动态权限校验过滤器
		if(dynamicSecurityService!=null){
			registry.and().addFilterBefore(dynamicSecurityFilter, FilterSecurityInterceptor.class);
		}
	}

	/**
	 * 有动态权限配置时添加动态权限校验过滤器
	 *
	 * @param auth 动态权限
	 * @throws Exception 异常信息
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService())
				.passwordEncoder(new BCryptPasswordEncoder());
	}
}
