package com.bonc.service.device.rpc.core;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.bonc.common.core.constant.CommonConsts;
import com.bonc.service.device.rpc.client.NettyClient;
import com.bonc.service.device.rpc.dataBridge.Request;
import com.bonc.service.device.rpc.dataBridge.Response;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.rpc.core
 * @Description: 代理，调用接口方法
 * @Author: dreamcc
 * @Date: 2019/12/17 14:29
 * @Version: V1.0
 */
@Slf4j
@Component
public class RPCFactory<T> implements InvocationHandler {

	private NettyClient client;

	@Autowired
	public RPCFactory(NettyClient client) {
		this.client = client;
	}


	@Override
	@SneakyThrows
	public Object invoke(Object proxy, Method method, Object[] args) {
		Request request = new Request();
		//设置类名称
		request.setClassName(method.getDeclaringClass().getSimpleName());
		//设置方法名
		request.setMethodName(method.getName());
		//设置参数
		request.setParameters(args);
		//设置参数类型
		request.setParameterTypes(method.getParameterTypes());
		//设置请求ID
		request.setId(StrUtil.toString(IdUtil.createSnowflake(0L, 0L).nextId()));
		//发生request请求
		Object result = client.send(request);
		//返回值类型
		Class<?> returnType = method.getReturnType();

		Response response = JSONUtil.toBean(result.toString(),Response.class);
		//判断返回结果
		if(response.getCode()== CommonConsts.FAILED){
			log.error("发生异常:{}",response.getErrorMsg());
			throw new Exception(response.getErrorMsg());
		}
		if(returnType.isPrimitive()||String.class.isAssignableFrom(returnType)){
			return response.getData();
		}else if(Collection.class.isAssignableFrom(returnType)){
			return JSONUtil.parseArray(response.getData(),false);
		}else if(Map.class.isAssignableFrom(returnType)){
			return JSONUtil.toBean(response.getData().toString(),Map.class);
		}else {
			Object data = response.getData();
			return JSONUtil.parseObj(data.toString(),false);
		}
	}
}
