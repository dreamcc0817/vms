package com.bonc.vms.gateway.entity;

import com.bonc.vms.gateway.util.Const;

/**
 * @Title: vms
 * @Package: com.bonc.vms.gateway.entity
 * @Description: 接收心跳信息
 * @Author: dreamcc
 * @Date: 2019/12/24 9:24
 * @Version: V1.0
 */
public class KeepAliveReceived extends AbstractMonitorMessageReceived{

	public KeepAliveReceived(){
		super.msgType= Const.Gateway.RPC_HEART_BEAT.getCode();
	}

	@Override
	public void process() {

	}
}
