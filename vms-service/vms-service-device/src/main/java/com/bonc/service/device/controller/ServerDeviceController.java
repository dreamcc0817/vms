package com.bonc.service.device.controller;

import com.bonc.common.core.util.R;
import com.bonc.service.device.dto.ServerDeviceDTO;
import com.bonc.service.device.service.IServerDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.controller
 * @Description: 服务器设备controller
 * @Author: dreamcc
 * @Date: 2019/12/17 16:57
 * @Version: V1.0
 */
@Api(tags = "服务器设备模块")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/server")
public class ServerDeviceController {

	private IServerDeviceService serverDeviceService;

	@ApiOperation("添加服务器设备")
	@PostMapping("/add")
	public R addServerDevice(ServerDeviceDTO serverDeviceDTO) {
		Boolean result = serverDeviceService.addServerDevice(serverDeviceDTO);
		return R.ok(result);
	}

}
