package com.dreamcc.service.device.rpc.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Title: vms
 * @Package: com.dreamcc.service.device.rpc.core
 * @Description: RPC服务扫描类
 * @Author: dreamcc
 * @Date: 2019/12/17 13:49
 * @Version: V1.0
 */
@Component
public class RPCScannerConfigurer implements BeanDefinitionRegistryPostProcessor {
	/**
	 * 扫描的包
	 */
	String basePackage = "com.dreamcc.service.device.rpc.service";

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		ClassPathRPCScanner scanner = new ClassPathRPCScanner(registry);
		scanner.setAnnotationClass(null);
		scanner.registerFilters();
		scanner.scan(StringUtils.tokenizeToStringArray(this.basePackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}
}
