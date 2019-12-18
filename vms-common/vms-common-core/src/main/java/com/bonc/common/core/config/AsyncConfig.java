package com.bonc.common.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @Title: vms
 * @Package: com.bonc.common.core.config
 * @Description: 配置线程池和启用异步
 * @Author: dreamcc
 * @Date: 2019/12/17 9:30
 * @Version: V1.0
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

	@Override
	public Executor getAsyncExecutor() {
		//定义线程池
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		//核心线程数
		threadPoolTaskExecutor.setCorePoolSize(10);
		//线程池最大线程数
		threadPoolTaskExecutor.setMaxPoolSize(30);
		//线程队列最大线程数
		threadPoolTaskExecutor.setQueueCapacity(2000);
		//初始化
		threadPoolTaskExecutor.initialize();
		return threadPoolTaskExecutor;
	}
}
