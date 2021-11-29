package com.dreamcc.service.alarm.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Title: vms
 * @Package: com.dreamcc.service.alarm.controller
 * @Description:
 * @Author: dreamcc
 * @Date: 2020/1/15 8:49
 * @Version: V1.0
 */
public interface TestService extends Remote {

	String helloWorld(String name) throws RemoteException;

}