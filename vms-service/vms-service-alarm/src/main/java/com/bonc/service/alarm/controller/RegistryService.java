package com.bonc.service.alarm.controller;

import lombok.SneakyThrows;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @Title: vms
 * @Package: com.bonc.service.alarm.controller
 * @Description:
 * @Author: dreamcc
 * @Date: 2020/1/15 8:47
 * @Version: V1.0
 */
public class RegistryService {
	@SneakyThrows
	public static void main(String[] args) {
		try {
			// 本地主机上的远程对象注册表Registry的实例,默认端口1099
			Registry registry = LocateRegistry.createRegistry(1099);
			// 创建一个远程对象
			HelloRegistryFacade hello = new HelloRegistryFacadeImpl();
			TestService test = new TestServiceImpl();
			// 把远程对象注册到RMI注册服务器上，并命名为HelloRegistry
			registry.bind("HelloRegistry", hello);
			registry.bind("test",test);
			System.out.println("======= 启动RMI服务成功! =======");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
