package com.bonc.service.device.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.service.device.entity.ServerUnit;
import com.bonc.service.device.mapper.ServerUnitMapper;
import com.bonc.service.device.service.IServerUnitService;
import com.bonc.service.device.util.DeviceConsts;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.service.impl
 * @Description: 服务单元业务逻辑处理类
 * @Author: dreamcc
 * @Date: 2020/1/2 16:41
 * @Version: V1.0
 */
@Slf4j
@Service("serverUnitService")
@AllArgsConstructor
public class ServerUnitServiceImpl extends ServiceImpl<ServerUnitMapper, ServerUnit> implements IServerUnitService {

	private final ServerUnitMapper serverUnitMapper;


	/**
	 * 修改设备在线状态
	 *
	 * @param host  ip地址
	 * @param type  设备类型
	 * @param port  端口号
	 * @param state 状态
	 * @return 0失败/1成功
	 */
	@Override
	public Integer modifyUnitState(String host, Integer type, Integer port, Integer state) {
		ServerUnit serverUnit = serverUnitMapper.findByIpAndType(host, type);
		serverUnit.setState(state);
		serverUnit.setPort(port);
		//设备状态不在线时设置为无效端口
		if (DeviceConsts.OFF_ONLINE.equals(state)) {
			serverUnit.setPort(DeviceConsts.INVALID_PORT);
		}
		Integer result = serverUnitMapper.updateById(serverUnit);
		return result;
	}
}
