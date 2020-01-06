package com.bonc.service.device.mapper;

import com.bonc.service.device.BaseTest;
import com.bonc.service.device.dto.ServerDeviceDTO;
import com.bonc.service.device.entity.ServerUnit;
import com.bonc.service.device.service.IServerDeviceService;
import org.junit.Assert;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collections;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.mapper
 * @Description:
 * @Author: dreamcc
 * @Date: 2020/1/6 17:20
 * @Version: V1.0
 */
@MapperScan(basePackages = {"com.bonc.service"})
public class ServerUnitMapperTest extends BaseTest {

	@Autowired
	private ServerUnitMapper serverUnitMapper;
	@Autowired
	@Qualifier("serverDeviceService")
	private IServerDeviceService serverDeviceService;

	@Test
	//@Rollback
	public void findByIpAndType() {
		String host = "192.168.1.111";
		Integer type = 1;
		ServerDeviceDTO serverDeviceDTO = new ServerDeviceDTO();
		serverDeviceDTO.setHost(host);
		serverDeviceDTO.setServerUnitType(Collections.singletonList(type));
		//serverDeviceService.addServerDevice(serverDeviceDTO);
		ServerUnit serverUnit = serverUnitMapper.findByIpAndType(host, type);
		Assert.assertNotNull(serverUnit);
	}
}