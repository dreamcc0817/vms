package com.bonc.upms.feign;

import com.bonc.common.core.constant.SecurityConsts;
import com.bonc.common.core.util.R;
import com.bonc.upms.entity.SysLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @Title: vms
 * @Package: com.bonc.upms.feign
 * @Description: 日志收集
 * @Author: dreamcc
 * @Date: 2020/1/5 15:06
 * @Version: V1.0
 */
@FeignClient(contextId = "remoteLogService")
public interface IRemoteLogService {

	/**
	 * 保存日志
	 *
	 * @param sysLog 日志实体
	 * @param form 内部调用标志
	 * @return succes\false
	 */
	@PostMapping("/log/add")
	R<Boolean> saveLog(@RequestBody SysLog sysLog, @RequestHeader(SecurityConsts.FROM) String form);
}
