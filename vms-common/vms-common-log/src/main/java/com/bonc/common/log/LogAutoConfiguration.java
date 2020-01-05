package com.bonc.common.log;

import com.bonc.common.log.aspect.SysLogAspect;
import com.bonc.common.log.event.SysLogListener;
import com.bonc.upms.feign.IRemoteLogService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Title: vms
 * @Package: com.bonc.common.log
 * @Description: 日志自动配置类
 * @Author: dreamcc
 * @Date: 2019/12/12 16:04
 * @Version: V1.0
 */
@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
public class LogAutoConfiguration {

	private final IRemoteLogService remoteLogService;

	/**
	 * 注册日志监听
	 *
	 * @return
	 */
	@Bean
	public SysLogListener sysLogListener() {
		return new SysLogListener(remoteLogService);
	}

	/**
	 * 注册日志切面
	 *
	 * @return
	 */
	@Bean
	public SysLogAspect sysLogAspect() {
		return new SysLogAspect();
	}
}
