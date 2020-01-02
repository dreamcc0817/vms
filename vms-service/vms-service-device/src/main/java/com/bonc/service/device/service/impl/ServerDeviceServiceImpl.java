package com.bonc.service.device.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.common.core.constant.CommonConstants;
import com.bonc.service.device.mapper.ServerDeviceMapper;
import com.bonc.service.device.dto.ServerDeviceDTO;
import com.bonc.service.device.entity.ServerDevice;
import com.bonc.service.device.entity.ServerUnitRef;
import com.bonc.service.device.service.IServerDeviceService;
import com.bonc.service.device.service.IServerUnitRefService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.service.impl
 * @Description: IServerDevice接口实现类
 * @Author: dreamcc
 * @Date: 2019/12/23 10:34
 * @Version: V1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class ServerDeviceServiceImpl extends ServiceImpl<ServerDeviceMapper, ServerDevice> implements IServerDeviceService {


	private final IServerUnitRefService serverUnitRefService;

	/**
	 * 添加服务器设备与服务单元
	 *
	 * @param serverDeviceDTO 服务器设备与服务单元DTO
	 * @return Boolean
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean addServerDevice(ServerDeviceDTO serverDeviceDTO) {
		ServerDevice serverDevice = new ServerDevice();
		//添加服务器设备基本信息
		BeanUtils.copyProperties(serverDeviceDTO, serverDevice);
		serverDevice.setCreateTime(LocalDateTime.now());
		serverDevice.setState(CommonConstants.STATUS_NORMAL);
		int addServerInfo = baseMapper.insert(serverDevice);
		//添加服务单元信息
		List<ServerUnitRef> serverUnitRefList = new ArrayList<>();
		if(serverDeviceDTO.getServerUnitId()!= null && serverDeviceDTO.getServerUnitId().size()>0){
			serverUnitRefList = serverDeviceDTO.getServerUnitId()
					.stream()
					.map(unitId -> {
						ServerUnitRef serverUnitRef = new ServerUnitRef();
						serverUnitRef.setServerId(serverDeviceDTO.getId());
						serverUnitRef.setUnitId(unitId);
						return serverUnitRef;
					}).collect(Collectors.toList());	
		}
		boolean addUnitResult = serverUnitRefService.saveBatch(serverUnitRefList);
		//判断保存结果
		if (addServerInfo > 0 && addUnitResult) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
}
