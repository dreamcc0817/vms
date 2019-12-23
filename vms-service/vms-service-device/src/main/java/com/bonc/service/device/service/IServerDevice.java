package com.bonc.service.device.service;

import com.bonc.service.device.entity.ServerDevice;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.service
 * @Description: 服务器设备逻辑处理
 * @Author: dreamcc
 * @Date: 2019/12/23 10:33
 * @Version: V1.0
 */
public interface IServerDevice {

	ServerDevice insert(ServerDevice serverDevice);

	void update(ServerDevice serverDevice);

	void delete(ServerDevice serverDevice);

	ServerDevice findOne(Query query);
}
