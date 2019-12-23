package com.bonc.service.device.controller;

import cn.hutool.core.util.IdUtil;
import com.bonc.service.device.entity.ServerDevice;
import com.bonc.service.device.service.IServerDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.controller
 * @Description: 服务器设备请求接口
 * @Author: dreamcc
 * @Date: 2019/12/17 16:57
 * @Version: V1.0
 */
@RestController
public class ServerDeviceController {

	@Autowired
	private IServerDevice serverDeviceService;


	@PostMapping("/save")
	public String insertServerDevice() {
		ServerDevice serverDevice = new ServerDevice();
		serverDevice.setId(IdUtil.createSnowflake(0, 0).nextId());
		serverDevice.setIp("11111");
		serverDevice.setDeviceType(1);
		String result = serverDeviceService.insert(serverDevice).toString();
		return result;
	}

	@PutMapping("/update")
	public String update(Long id) {
		ServerDevice serverDevice = new ServerDevice();
		serverDevice.setId(id);
		serverDevice.setDeviceType(2);
		serverDeviceService.update(serverDevice);
		return "update";
	}

	@GetMapping("/get")
	public String find(Long id) {
		Criteria criteria = Criteria.where("id").is(id);
		Query query = Query.query(criteria);
		ServerDevice serverDevice = serverDeviceService.findOne(query);
		return serverDevice.toString();
	}

	@DeleteMapping("/delete")
	public String delete(Long id) {
		ServerDevice serverDevice = new ServerDevice();
		serverDevice.setId(id);
		serverDeviceService.delete(serverDevice);
		return "delete";
	}
}
