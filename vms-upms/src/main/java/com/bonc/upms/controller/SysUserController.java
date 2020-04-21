package com.bonc.upms.controller;


import com.bonc.common.core.util.R;
import com.bonc.upms.dto.LoginParamDTO;
import com.bonc.upms.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

	@Value("${jwt.tokenHead}")
	private String tokenHead;
	@Autowired
	private ISysUserService sysUserService;

	@ApiOperation(value = "用户登录")
	@PostMapping("/login")
	public R login(@RequestBody LoginParamDTO loginParam){
		String token = sysUserService.login(loginParam.getUsername(),loginParam.getPassword());
		if (token == null) {
			return R.failed("用户名或密码错误");
		}
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", token);
		tokenMap.put("tokenHead", tokenHead);
		return R.ok(tokenMap);
	}
}
