package com.bonc.service.alarm.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Title: vms
 * @Package: com.bonc.service.alarm.controller
 * @Description:
 * @Author: dreamcc
 * @Date: 2020/1/15 8:49
 * @Version: V1.0
 */
public interface TestService extends Remote {

	String helloWorld(String name) throws RemoteException;

}