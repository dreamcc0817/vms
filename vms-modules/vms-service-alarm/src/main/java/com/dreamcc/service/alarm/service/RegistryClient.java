package com.dreamcc.service.alarm.service;

import com.dreamcc.service.alarm.controller.HelloRegistryFacade;
import com.dreamcc.service.alarm.controller.TestService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegistryClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(9124);
            HelloRegistryFacade hello = (HelloRegistryFacade) registry.lookup("syncData");
            TestService testService = (TestService)registry.lookup("test");

            String response = testService.helloWorld("ZhenJin");
            System.out.println("=======> " + response + " <=======");
        } catch (NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }
}