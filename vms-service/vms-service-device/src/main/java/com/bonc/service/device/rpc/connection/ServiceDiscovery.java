package com.bonc.service.device.rpc.connection;

import lombok.AllArgsConstructor;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//TODO zookeeper地址
/**
 * @Title: vms
 * @Package: com.bonc.service.device.rpc.client
 * @Description: 服务发现
 * @Author: dreamcc
 * @Date: 2019/12/17 11:37
 * @Version: V1.0
 */

@Component
@AllArgsConstructor
public class ServiceDiscovery {
	/**
	 * zookeeper注册中心地址
	 */
	private String registryAddress = "127.0.0.1:9888";
	/**
	 * 连接管理
	 */
	ConnectManage connectManage;
	/**
	 * 服务器地址
	 */
	private volatile List<String> addressList = new ArrayList<>();
	/**
	 * 注册中心地址
	 */
	private static final String ZK_REGISTRY_PATH = "/rpc";
	/**
	 * ZK客户端
	 */
	private ZkClient client;

	@Autowired
	public ServiceDiscovery(ConnectManage connectManage) {
		this.connectManage = connectManage;
		updateConnectedServer();
	}

	/**
	 * 更新连接服务
	 */
	private void updateConnectedServer() {
		addressList.add(registryAddress);
		connectManage.updateConnectServer(addressList);
	}
}
