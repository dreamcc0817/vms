package com.bonc.service.alarm.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloRegistryFacade extends Remote {

    String helloWorld(String name) throws RemoteException;

}