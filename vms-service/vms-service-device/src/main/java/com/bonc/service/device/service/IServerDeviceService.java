package com.bonc.service.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bonc.service.device.dto.ServerDeviceDTO;
import com.bonc.service.device.entity.ServerDevice;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.service
 * @Description: 服务器设备逻辑处理接口
 * @Author: dreamcc
 * @Date: 2019/12/23 10:33
 * @Version: V1.0
 */
public interface IServerDeviceService extends IService<ServerDevice> {

	/**
	 * 增加服务器设备与服务单元
	 *
	 * @param serverDeviceDTO 服务器设备与服务单元DTO
	 * @return Boolean
	 */
	Boolean addServerDevice(ServerDeviceDTO serverDeviceDTO);
}
