package com.bonc.vms.gateway.rpc.service.impl;

import com.bonc.vms.gateway.cache.IoTDeviceCache;
import com.bonc.vms.gateway.rpc.annotation.RPCService;
import com.bonc.vms.gateway.rpc.service.IDeviceService;
import io.netty.channel.Channel;

/**
 * @Title: vms
 * @Package: com.bonc.vms.gateway.service.impl
 * @Description: 设备操作业务类
 * @Author: dreamcc
 * @Date: 2019/12/13 14:37
 * @Version: V1.0
 */
@RPCService
public class DeviceServiceImpl implements IDeviceService {

	@Override
	public String findDevice(String ip) {
		Channel channel = IoTDeviceCache.get(ip);
		return null;
	}
}
