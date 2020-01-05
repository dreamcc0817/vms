package com.bonc.upms.controller;


import com.bonc.common.core.util.R;
import com.bonc.upms.entity.SysLog;
import com.bonc.upms.service.ISysLogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Title: vms
 * @Package: com.bonc.upms.controller
 * @Description: 日志表 前端控制器
 * @Author: dreamcc
 * @Date: 2020/1/4 19:19
 * @Version: V1.0
 */
@RestController
@RequestMapping("/upms/sys-log")
@AllArgsConstructor
public class SysLogController {

	private final ISysLogService sysLogService;

	@PostMapping("/add")
	public R save(@Valid @RequestBody SysLog sysLog) {
		return R.ok(sysLogService.save(sysLog));
	}
}
