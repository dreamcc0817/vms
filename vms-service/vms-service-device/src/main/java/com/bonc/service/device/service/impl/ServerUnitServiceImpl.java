package com.bonc.service.device.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.service.device.entity.ServerUnit;
import com.bonc.service.device.mapper.ServerUnitMapper;
import com.bonc.service.device.service.IServerUnitService;
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
@Service
public class ServerUnitServiceImpl extends ServiceImpl<ServerUnitMapper, ServerUnit> implements IServerUnitService {

	/**
	 * 更新设备状态
	 *
	 * @param message mq消息
	 * @return 状态
	 */
	@Override
	public Integer checkDeviceState(String message) {

		log.info("收到mq消息{}", message);
		return null;
	}
}
