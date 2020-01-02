package com.bonc.service.device.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.config
 * @Description: mybatisplus插件配置类
 * @Author: dreamcc
 * @Date: 2020/1/2 10:11
 * @Version: V1.0
 */
@Configuration
@MapperScan("com.bonc.service.device.mapper")
public class MybaitsPlusConfiguer {

	/**
	 * 分页插件
	 *
	 * @return PaginationInterceptor
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

}
