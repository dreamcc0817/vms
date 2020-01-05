package com.bonc.common.log.event;

import com.bonc.common.core.constant.SecurityConsts;
import com.bonc.upms.entity.SysLog;
import com.bonc.upms.feign.IRemoteLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @Title: vms
 * @Package: com.bonc.common.log
 * @Description: 异步监听日志事件
 * @Author: dreamcc
 * @Date: 2019/12/12 16:16
 * @Version: V1.0
 */
@Slf4j
@AllArgsConstructor
public class SysLogListener {

	private final IRemoteLogService remoteLogService;

	/**
	 * 监听日志事件，调用远程服务保存日志
	 *
	 * @param event
	 */
	@Async
	@Order
	@EventListener(SysLogEvent.class)
	public void saveSysLog(SysLogEvent event) {
		SysLog sysLog = (SysLog) event.getSource();
		remoteLogService.saveLog(sysLog, SecurityConsts.FROM_IN);
	}
}
