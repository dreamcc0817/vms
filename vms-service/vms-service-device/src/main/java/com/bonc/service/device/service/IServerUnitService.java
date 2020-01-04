package com.bonc.service.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bonc.service.device.entity.ServerUnit;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.service
 * @Description: 服务单元service
 * @Author: dreamcc
 * @Date: 2020/1/2 16:39
 * @Version: V1.0
 */
public interface IServerUnitService extends IService<ServerUnit> {
	/**
	 * 检测设备在线状态
	 * @param message mq消息
	 * @return 状态
	 */
	Integer checkDeviceState(String message);
}
