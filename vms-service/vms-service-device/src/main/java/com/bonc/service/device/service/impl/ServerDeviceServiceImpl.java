package com.bonc.service.device.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bonc.common.core.constant.CommonConsts;
import com.bonc.service.device.dto.ServerDeviceDTO;
import com.bonc.service.device.entity.ServerDevice;
import com.bonc.service.device.entity.ServerUnit;
import com.bonc.service.device.entity.ServerUnitRef;
import com.bonc.service.device.mapper.ServerDeviceMapper;
import com.bonc.service.device.service.IServerDeviceService;
import com.bonc.service.device.service.IServerUnitRefService;
import com.bonc.service.device.service.IServerUnitService;
import com.bonc.service.device.util.DeviceConst;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

	private final IServerUnitService serverUnitService;

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
		serverDevice.setState(CommonConsts.STATUS_NORMAL);
		serverDevice.setDelFlag(CommonConsts.STATUS_NORMAL);
		int addServerInfo = baseMapper.insert(serverDevice);
		if (serverDeviceDTO.getServerUnitType() == null || serverDeviceDTO.getServerUnitType().size() <= 0) {
			if (addServerInfo > 0) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		}
		//添加服务单元信息
		List<ServerUnit> serverUnitList = serverDeviceDTO.getServerUnitType()
				.stream()
				.map(unitType -> {
					ServerUnit serverUnit = new ServerUnit();
					serverUnit.setUnitName(serverDevice.getServerName() + "_" + DeviceConst.UnitType.getName(unitType));
					serverUnit.setType(unitType);
					serverUnit.setPort(DeviceConst.INVALID_PORT);
					serverUnit.setLoadNum(DeviceConst.LOAD_DUFAULT);
					serverUnit.setState(DeviceConst.OFF_ONLINE);
					serverUnit.setCreateTime(LocalDateTime.now());
					return serverUnit;
				})
				.collect(Collectors.toList());
		boolean addUnitResult = serverUnitService.saveBatch(serverUnitList);
		//添加服务器与服务单元关联信息
		List<ServerUnitRef> serverUnitRefList = serverUnitList
				.stream()
				.map(unit -> {
					ServerUnitRef serverUnitRef = new ServerUnitRef();
					serverUnitRef.setServerId(serverDevice.getId());
					serverUnitRef.setUnitId(unit.getId());
					return serverUnitRef;
				})
				.collect(Collectors.toList());
		boolean addRefResult = serverUnitRefService.saveBatch(serverUnitRefList);
		//判断保存结果
		if (addServerInfo > 0 && addUnitResult && addRefResult) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
}
