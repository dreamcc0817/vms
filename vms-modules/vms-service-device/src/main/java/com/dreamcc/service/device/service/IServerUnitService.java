package com.dreamcc.service.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamcc.service.device.entity.ServerUnit;

/**
 * @Title: vms
 * @Package: com.dreamcc.service.device.service
 * @Description: 服务单元service
 * @Author: dreamcc
 * @Date: 2020/1/2 16:39
 * @Version: V1.0
 */
public interface IServerUnitService extends IService<ServerUnit> {
	/**
	 * 修改服务单元状态
	 *
	 * @param host    ip地址
	 * @param type  设备类型
	 * @param port  端口号
	 * @param state 状态
	 * @return 0失败/1成功
	 */
	Integer modifyUnitState(String host, Integer type, Integer port, Integer state);
}
