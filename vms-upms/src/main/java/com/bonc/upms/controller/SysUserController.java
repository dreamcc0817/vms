package com.bonc.upms.controller;


import com.bonc.upms.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: vms
 * @Package: com.bonc.upms.controller
 * @Description: 用户表 前端控制器
 * @Author: dreamcc
 * @Date: 2020/1/4 19:19
 * @Version: V1.0
 */
@RestController
@RequestMapping("/upms/sys-user")
public class SysUserController {

	@Autowired
	private ISysUserService sysUserService;
}
