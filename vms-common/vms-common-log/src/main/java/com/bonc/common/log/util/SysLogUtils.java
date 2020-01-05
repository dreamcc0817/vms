package com.bonc.common.log.util;

import com.bonc.upms.entity.SysLog;
import lombok.experimental.UtilityClass;

/**
 * @Title: vms
 * @Package: com.bonc.common.log.util
 * @Description: 系统日志工具类
 * @Author: dreamcc
 * @Date: 2019/12/31 14:14
 * @Version: V1.0
 */
@UtilityClass
public class SysLogUtils {


	public SysLog getSysLog() {
		SysLog sysLog = new SysLog();
		return sysLog;
	}
}
