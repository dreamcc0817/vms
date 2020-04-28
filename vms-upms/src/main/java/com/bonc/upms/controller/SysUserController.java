package com.bonc.upms.controller;


import com.bonc.common.core.util.R;
import com.bonc.upms.dto.LoginParamDTO;
import com.bonc.upms.dto.SysUserDTO;
import com.bonc.upms.entity.SysUser;
import com.bonc.upms.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
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

	/**
	 * 用户登录，并返回token
	 *
	 * @param loginParam 登录参数
	 * @return token
	 */
	@ApiOperation(value = "用户登录")
	@PostMapping("/login")
	public R<Map<String, String>> login(@RequestBody LoginParamDTO loginParam) {
		String token = sysUserService.login(loginParam.getUsername(), loginParam.getPassword());
		if (token == null) {
			return R.failed("用户名或密码错误");
		}
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", token);
		tokenMap.put("tokenHead", tokenHead);
		return R.ok(tokenMap);
	}

	/**
	 * 刷新token，并返回token
	 *
	 * @param request 请求
	 * @return token
	 */
	@ApiOperation(value = "刷新token")
	@GetMapping(value = "/refresh-token")
	public R<Map<String, String>> refreshToken(HttpServletRequest request) {
		String token = request.getHeader(tokenHead);
		String refreshToken = sysUserService.refreshToken(token);
		if (refreshToken == null) {
			return R.failed("token过期");
		}
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", refreshToken);
		tokenMap.put("tokenHead", tokenHead);
		return R.ok(tokenMap);
	}

	/**
	 * 获取当前登录用户信息
	 *
	 * @param principal 用户信息
	 * @return 当前用户信息
	 */
	@ApiOperation(value = "获取当前登录用户信息")
	@GetMapping(value = "/info")
	public R<Map<String, Object>> getSysUserInfo(Principal principal) {
		if (principal == null) {
			return R.unauthorized(null);
		}
		String username = principal.getName();
		SysUser sysUser = sysUserService.getSysUserByUsername(username);
		Map<String, Object> result = new HashMap<>();
		result.put("username", username);
		result.put("roles", null);
		result.put("menus", null);
		return R.ok(result);
	}

	/**
	 * 注册用户信息
	 *
	 * @param userDTO    用户信息
	 * @param bindResult 验证参数
	 * @return 用户信息
	 */
	@ApiOperation(value = "注册用户信息")
	@PostMapping(value = "/add")
	public R<SysUser> register(@Validated @RequestBody SysUserDTO userDTO, BindResult bindResult) {
		SysUser register = sysUserService.register(userDTO);
		if (register == null) {
			R.failed();
		}
		return R.ok(register);
	}

	/**
	 * 给用户分配权限
	 *
	 * @param userId        用户ID
	 * @param permissionIds 权限ID
	 * @return 是否成功
	 */
	public R<Integer> updatePermission(@RequestParam Long userId,
									   @RequestParam("permissionIds") List<Long> permissionIds) {

		int count = this.sysUserService.updatePermission(userId,permissionIds);

		return R.ok();
	}
}
