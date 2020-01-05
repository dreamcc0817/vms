package com.bonc.common.log.aspect;

import com.bonc.common.core.util.SpringContextHolder;
import com.bonc.common.log.annotation.SysLog;
import com.bonc.common.log.event.SysLogEvent;
import com.bonc.common.log.util.SysLogUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @Title: vms
 * @Package: com.bonc.common.log.aspect
 * @Description: 操作日志使用Spring event 异步入库
 * @Author: dreamcc
 * @Date: 2019/12/12 16:08
 * @Version: V1.0
 */
@Aspect
@Slf4j
public class SysLogAspect {

	@Around("@annotation(sysLog)")
	@SneakyThrows
	public Object around(ProceedingJoinPoint point, SysLog sysLog) {
		String className = point.getTarget().getClass().getName();
		String methodName = point.getSignature().getName();
		log.debug("[类名]:{},[方法]:{}", className, methodName);
		com.bonc.upms.entity.SysLog logVo = SysLogUtils.getSysLog();
		logVo.setTitle(sysLog.value());
		Long startTime = System.currentTimeMillis();
		Object obj = point.proceed();
		Long endTimd = System.currentTimeMillis();
		logVo.setTime(endTimd - startTime);
		SpringContextHolder.publishEvent(new SysLogEvent(logVo));
		return obj;
	}
}
