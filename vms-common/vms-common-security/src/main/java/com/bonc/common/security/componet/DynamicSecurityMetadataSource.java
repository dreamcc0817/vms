package com.bonc.common.security.componet;

import cn.hutool.core.util.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Title: vms
 * @Package: com.cms.common.security.componet
 * @Description: 动态权限数据源，用于获取动态权限规则
 * @Author: dreamcc
 * @Date: 2020/4/19 18:33
 * @Version: V1.0
 */
//@Component
//@ConditionalOnBean(name = "dynamicSecurityService")
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private Map<String, ConfigAttribute> configAttributeMap = null;
	@Autowired
	private DynamicSecurityService dynamicSecurityService;
	@PostConstruct
	public void loadDataSource() {
		configAttributeMap = dynamicSecurityService.loadDataSource();
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if(configAttributeMap == null){
			this.loadDataSource();
		}
		List<ConfigAttribute> configAttributes = new ArrayList<>();
		//获取当前访问路径
		String url = ((FilterInvocation)object).getRequestUrl();
		String path = URLUtil.getPath(url);
		PathMatcher pathMatcher = new AntPathMatcher();
		//获取访问该路径所需资源
		for (Map.Entry<String,ConfigAttribute> patternEntry : configAttributeMap.entrySet()) {
			String pattern = patternEntry.getKey();
			if (pathMatcher.match(pattern, path)) {
				configAttributes.add(configAttributeMap.get(patternEntry.getValue()));
			}
		}
		return configAttributes;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	/**
	 * 清空动态权限数据源
	 */
	public void clearDataSource() {
		configAttributeMap.clear();
		configAttributeMap = null;
	}
}
