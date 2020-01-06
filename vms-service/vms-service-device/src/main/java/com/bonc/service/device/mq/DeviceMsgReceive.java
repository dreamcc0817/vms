package com.bonc.service.device.mq;

import cn.hutool.core.convert.Convert;
import com.bonc.service.device.service.IServerUnitService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Title: vms
 * @Package: com.bonc.service.device
 * @Description: 设备MQ消息接收
 * @Author: dreamcc
 * @Date: 2020/1/6 11:17
 * @Version: V1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class DeviceMsgReceive {

	private IServerUnitService serverUnitService;

	@StreamListener(Sink.INPUT)
	public void checkDeviceState(String message) {
		log.info("收到mq消息{}", message);
		Map<String, Object> receiveMessage = Convert.toMap(String.class, Object.class, message);
		//获取ip,包含主机与端口号
		String[] ipAddr = receiveMessage.get("ip").toString().split(":");
		String host = ipAddr[0];
		Integer port = Integer.parseInt(ipAddr[1]);
		//获取设备类型
		Integer type = Integer.parseInt(((Map<String, Object>) receiveMessage.get("msg")).get("type").toString());
		//获取设备状态
		Integer state = Integer.parseInt(receiveMessage.get("state").toString());
		//修改服务单元状态
		serverUnitService.modifyUnitState(host, type, port, state);
	}
}
