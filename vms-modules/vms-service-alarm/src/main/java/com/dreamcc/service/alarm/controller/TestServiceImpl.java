package com.dreamcc.service.alarm.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Title: vms
 * @Package: com.dreamcc.service.alarm.controller
 * @Description:
 * @Author: dreamcc
 * @Date: 2020/1/15 8:50
 * @Version: V1.0
 */
public class TestServiceImpl extends UnicastRemoteObject implements TestService {
	public TestServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public String helloWorld(String name) {
		return "[Registry] hello! " + name;
	}
}
