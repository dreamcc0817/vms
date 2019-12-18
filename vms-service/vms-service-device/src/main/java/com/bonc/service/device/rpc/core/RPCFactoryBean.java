package com.bonc.service.device.rpc.core;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Proxy;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.rpc.core
 * @Description: 生成Bean
 * @Author: dreamcc
 * @Date: 2019/12/17 14:07
 * @Version: V1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public class RPCFactoryBean<T> implements FactoryBean<T> {

	/**
	 * RPC接口
	 */
	private Class<T> rpcInterface;

	@Autowired
	private RPCFactory<T> rpcFactory;

	public RPCFactoryBean(Class<T> rpcInterface) {
		this.rpcInterface = rpcInterface;
	}
	/**
	 * 返回对象实例
	 *
	 * @return 对象实例
	 * @throws Exception 异常
	 */
	@Override
	public T getObject() throws Exception {
		return getRpc();
	}

	/**
	 * 返回bean类型
	 *
	 * @return bean类型
	 */
	@Override
	public Class<?> getObjectType() {
		return this.rpcInterface;
	}

	/**
	 * 返回对象实例
	 *
	 * @return 对象实例
	 * @throws Exception 异常
	 */
	public <T> T getRpc() {
		return (T) Proxy.newProxyInstance(rpcInterface.getClassLoader(), new Class[]{rpcInterface}, rpcFactory);
	}
}
