package com.bonc.common.log.event;

import com.bonc.upms.entity.SysLog;
import org.springframework.context.ApplicationEvent;

/**
 * @Title: vms
 * @Package: com.bonc.common.log.aspect
 * @Description: 系统事件日志
 * @Author: dreamcc
 * @Date: 2019/12/12 16:17
 * @Version: V1.0
 */
public class SysLogEvent extends ApplicationEvent {

	public SysLogEvent(SysLog source) {
		super(source);
	}
}
