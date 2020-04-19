package com.cms.common.security.componet;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @Title: vms
 * @Package: com.cms.common.security.componet
 * @Description: 动态权限相关业务类
 * @Author: dreamcc
 * @Date: 2020/4/19 17:27
 * @Version: V1.0
 */
public interface DynamicSecurityService {

	/**
	 * 加载资源ANT通配符和资源对应MAP
	 */
	Map<String, ConfigAttribute> loadDataSource();
}
