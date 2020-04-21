package com.bonc.upms.controller;


import com.bonc.common.core.util.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: vms
 * @Package: com.bonc.upms.controller
 * @Description: 部门管理 前端控制器
 * @Author: dreamcc
 * @Date: 2020/1/4 19:19
 * @Version: V1.0
 */
@RestController
@RequestMapping("/upms/sys-dept")
public class SysDeptController {

	@PreAuthorize("hasAuthority('upms:dept:insert')")
	@PostMapping("/insert")
	public R insertSysDept(){
		return R.ok();
	}
}
