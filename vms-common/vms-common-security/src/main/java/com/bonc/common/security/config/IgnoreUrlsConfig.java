package com.bonc.common.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: vms
 * @Package: com.cms.common.security.config
 * @Description: 用于配置不需要保护的资源路径
 * @Author: dreamcc
 * @Date: 2020/4/19 17:38
 * @Version: V1.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "secure.ignored")
//@Component
public class IgnoreUrlsConfig {
	private List<String> urls = new ArrayList<>();
}
