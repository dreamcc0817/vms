package com.bonc.vms.gateway.rpc.service;

/**
 * @Title: vms
 * @Package: com.bonc.vms.gateway.service
 * @Description: 设备服务
 * @Author: dreamcc
 * @Date: 2019/12/13 14:33
 * @Version: V1.0
 */
public interface IDeviceService {

	/**
	 * 查询设备连接信息
	 *
	 * @return
	 */
	String findDevice(String ip);
}
