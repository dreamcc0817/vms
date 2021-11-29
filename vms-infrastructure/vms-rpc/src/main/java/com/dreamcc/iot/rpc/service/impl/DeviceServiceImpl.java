package com.dreamcc.iot.rpc.service.impl;

import com.dreamcc.iot.rpc.annotation.RPCService;
import com.dreamcc.iot.cache.IoTDeviceCache;
import com.dreamcc.iot.rpc.service.IDeviceService;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @Title: vms
 * @Package: com.dreamcc.vms.gateway.service.impl
 * @Description: 设备操作业务类
 * @Author: dreamcc
 * @Date: 2019/12/13 14:37
 * @Version: V1.0
 */
@RPCService
@Component
public class DeviceServiceImpl implements IDeviceService {

	@Override
	public String findDevice(String ip) {
		Channel channel = IoTDeviceCache.get(ip);
		String s = (channel == null) ? "Hello Netty" : channel.remoteAddress().toString();
		return s;
	}
}
