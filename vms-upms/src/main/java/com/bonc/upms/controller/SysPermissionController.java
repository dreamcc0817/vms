package com.bonc.upms.controller;

import com.bonc.common.core.util.R;
import com.bonc.upms.entity.SysPermission;
import com.bonc.upms.service.ISysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: vms
 * @Package: com.bonc.upms.controller
 * @Description: 权限controller
 * @Author: dreamcc
 * @Date: 2020/4/23 21:05
 * @Version: V1.0
 */
@RestController
@RequestMapping("/sys-permission")
public class SysPermissionController {
	@Autowired
	private ISysPermissionService sysPermissionService;

	/**
	 * 添加权限
	 * @param permission 添加权限
	 * @return 是否添加成功
	 */
	@PostMapping("/add")
	public R add(@RequestBody SysPermission permission){
		boolean result = sysPermissionService.save(permission);
		if(result){
			return R.ok();
		}else {
			return R.failed();
		}
	}
	/**
	 * 修改权限
	 * @param permission 添加权限
	 * @return 是否添加成功
	 */
	@PostMapping("/update")
	public R update(@RequestBody SysPermission permission){
		boolean result = sysPermissionService.updateById(permission);
		if(result){
			return R.ok();
		}else {
			return R.failed();
		}
	}
}
