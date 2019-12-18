package com.bonc.service.device.rpc.core;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.rpc.core
 * @Description: RPC服务扫描类
 * @Author: dreamcc
 * @Date: 2019/12/17 13:57
 * @Version: V1.0
 */
@Slf4j
public class ClassPathRPCScanner extends ClassPathBeanDefinitionScanner {

	private RPCFactoryBean<?> rpcFactoryBean = new RPCFactoryBean<>();

	@Setter
	private Class<? extends Annotation> annotationClass;

	public ClassPathRPCScanner(BeanDefinitionRegistry registry) {
		super(registry);
	}

	/**
	 * 扫描spring容器中的bean，注册为BeanDefinition的集合
	 *
	 * @param basePackages 扫描的包
	 * @return BeanDefinition的集合
	 */
	@Override
	protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
		//默认扫描方法
		Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
		if (beanDefinitions.isEmpty()) {
			log.warn("没有找到RPC服务");
		} else {
			processBeanDefinition(beanDefinitions);
		}
		return beanDefinitions;
	}

	/**
	 * 注册拦截器
	 */
	public void registerFilters() {
		//是否允许放开所有接口
		boolean acceptAllInterfaces = true;
		if (this.annotationClass != null) {
			//增加注解拦截器
			addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
			acceptAllInterfaces = false;
		}

		if (acceptAllInterfaces) {
			addIncludeFilter((metadataReader, metadataReaderFactory) -> true);
		}
		//排除package-info.java
		addExcludeFilter((metadataReader, metadataReaderFactory) -> {
			String className = metadataReader.getClassMetadata().getClassName();
			return className.endsWith("package-info");
		});
	}

	/**
	 * 配置注册为BeanDefinition对象
	 *
	 * @param beanDefinitions 需要配置的对象
	 */
	private void processBeanDefinition(Set<BeanDefinitionHolder> beanDefinitions) {
		GenericBeanDefinition definition;
		for (BeanDefinitionHolder holder : beanDefinitions) {
			definition = (GenericBeanDefinition) holder.getBeanDefinition();
			definition.getConstructorArgumentValues().addGenericArgumentValue(definition.getBeanClassName());
			definition.setBeanClass(this.rpcFactoryBean.getClass());
			definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
		}
	}

	/**
	 * 判断资源是否为候选的组件
	 *
	 * @param beanDefinition 对象
	 * @return 是否为候选的组件
	 */
	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
	}
}
