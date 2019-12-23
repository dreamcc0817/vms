package com.bonc.service.device.service.impl;

import com.bonc.service.device.dao.ServerDeviceDao;
import com.bonc.service.device.entity.ServerDevice;
import com.bonc.service.device.service.IServerDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.service.impl
 * @Description: IServerDevice接口实现类
 * @Author: dreamcc
 * @Date: 2019/12/23 10:34
 * @Version: V1.0
 */
@Service
public class ServerDeviceImpl implements IServerDevice {

	private final ServerDeviceDao testDao;

	@Autowired
	public ServerDeviceImpl( ServerDeviceDao testDao) {
		this.testDao = testDao;
	}

	/**
	 * 新增服务器设备
	 *
	 * @param serverDevice 服务器设备
	 * @return
	 */
	@Override
	public ServerDevice insert(ServerDevice serverDevice) {

		serverDevice = (ServerDevice)testDao.save(serverDevice);

		return serverDevice;
	}

	@Override
	public void update(ServerDevice serverDevice) {
		testDao.update(serverDevice);
	}

	@Override
	public void delete(ServerDevice serverDevice) {
		testDao.remove(serverDevice);
	}

	@Override
	public ServerDevice findOne(Query query) {
		return (ServerDevice) testDao.findOne(query);
	}
}
