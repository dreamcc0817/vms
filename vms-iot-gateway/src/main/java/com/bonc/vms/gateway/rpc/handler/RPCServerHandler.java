package com.bonc.vms.gateway.rpc.handler;

import cn.hutool.json.JSONUtil;
import com.bonc.vms.gateway.rpc.dataBridge.Request;
import com.bonc.vms.gateway.rpc.dataBridge.Response;
import com.bonc.vms.gateway.util.Const;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Title: vms
 * @Package: com.bonc.vms.gateway.rpc.handler
 * @Description: RPC服务Handler
 * @Author: dreamcc
 * @Date: 2019/12/13 16:34
 * @Version: V1.0
 */
@Slf4j
public class RPCServerHandler extends ChannelInboundHandlerAdapter {
	/**
	 * RPC服务类
	 */
	private final Map<String, Object> serviceBeans;

	public RPCServerHandler(Map<String, Object> serviceBeans) {
		this.serviceBeans = serviceBeans;
	}

	/**
	 * RPC注册连接
	 *
	 * @param ctx
	 * @throws Exception
	 */
	@SneakyThrows
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		log.info("【RPC】客户端{}连接成功！", ctx.channel().remoteAddress());
	}

	/**
	 * 读取RPC消息
	 *
	 * @param ctx
	 * @param msg
	 * @throws Exception
	 */
	@SneakyThrows
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		Request request = JSONUtil.toBean(msg.toString(), Request.class);
		if (Const.Gateway.RPC_HEART_BEAT.getValue().equals(request.getMethodName())) {
			log.info("【RPC】客户端{}心跳连接", ctx.channel().remoteAddress());
		} else {
			log.info("【RPC】客户端{}请求接口:{},方法名{}", ctx.channel().remoteAddress(), request.getClassName(), request.getMethodName());
			Response response = new Response();
			response.setRequestId(request.getId());
			Object result = this.handler(request);
			response.setData(result);
			ctx.writeAndFlush(response);
		}
	}

	/**
	 * RPC断开连接
	 *
	 * @param ctx
	 * @throws Exception
	 */
	@SneakyThrows
	@Override
	public void channelInactive(ChannelHandlerContext ctx) {
		ctx.channel().close();
		log.info("【RPC】客户端{}断开！", ctx.channel().remoteAddress());
	}

	/**
	 * 方法调用
	 *
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@SneakyThrows
	public Object handler(Request request) {
		String className = request.getClassName();
		Object serviceBean = serviceBeans.get(className);

		if (serviceBean != null) {
			Class<?> serviceClass = serviceBean.getClass();
			String methodName = request.getMethodName();
			Class<?>[] parameterTypes = request.getParameterTypes();
			Object[] parameters = request.getParameters();

			Method method = serviceClass.getMethod(methodName, parameterTypes);
			method.setAccessible(true);
			return method.invoke(serviceBean, getParameters(parameterTypes, parameters));
		} else {
			log.error("未找到服务接口，请检查配置！{}#{}", className, request.getMethodName());
			throw new Exception("未找到服务接口，请检查配置！");
		}
	}

	/**
	 * 获取参数列表
	 *
	 * @param parameterTypes
	 * @param parameters
	 * @return
	 */
	private Object[] getParameters(Class<?>[] parameterTypes, Object[] parameters) {
		if (parameters == null || parameters.length == 0) {
			return parameters;
		} else {
			Object[] newParameter = new Object[parameters.length];
			for (int i = 0; i < parameters.length; i++) {
				newParameter[i] = JSONUtil.toBean(parameters[i].toString(), parameterTypes[i]);
			}
			return newParameter;
		}
	}


}
