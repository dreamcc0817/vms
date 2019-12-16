package com.bonc.vms.gateway.rpc.dataBridge;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: vms
 * @Package: com.bonc.vms.gateway.rpc.dataBridge
 * @Description: 请求信息
 * @Author: dreamcc
 * @Date: 2019/12/13 15:11
 * @Version: V1.0
 */
@Data
public class Request implements Serializable {

	private static final long serialVersionUID = 4428843963026677373L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * 类名
	 */
	private String className;
	/**
	 * 函数名称
	 */
	private String methodName;
	/**
	 * 参数类型
	 */
	private Class<?>[] parameterTypes;
	/**
	 * 参数列表
	 */
	private Object[] parameters;
}
