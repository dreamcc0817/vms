package com.bonc.common.security.componet;

import cn.hutool.core.collection.CollUtil;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

/**
 * @Title: vms
 * @Package: com.cms.common.security.componet
 * @Description: 动态权限决策管理器，用于判断用户是否有访问权限
 * @Author: dreamcc
 * @Date: 2020/4/19 18:57
 * @Version: V1.0
 */
//@Component
//@ConditionalOnBean(name = "dynamicSecurityService")
public class DynamicAccessDecisionManager implements AccessDecisionManager {
	@Override
	public void decide(Authentication authentication, Object object,
					   Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (CollUtil.isEmpty(configAttributes)) {
			//当接口未被配置资源是直接放行
			return;
		}
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while (iterator.hasNext()) {
			ConfigAttribute configAttribute = iterator.next();
			//将访问所需资源或用户拥有资源进行比对
			String needAuthority = configAttribute.getAttribute();
			for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
				if (needAuthority.trim().equals(grantedAuthority.getAuthority())) {
					return;
				}
			}
		}
		throw new AccessDeniedException("抱歉，您没有访问权限");

	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
