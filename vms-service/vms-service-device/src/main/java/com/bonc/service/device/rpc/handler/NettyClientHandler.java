package com.bonc.service.device.rpc.handler;

import cn.hutool.json.JSONUtil;
import com.bonc.service.device.rpc.connection.ConnectManage;
import com.bonc.service.device.rpc.dataBridge.Request;
import com.bonc.service.device.rpc.dataBridge.Response;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.rpc.handler
 * @Description: nettyClient处理类
 * @Author: dreamcc
 * @Date: 2019/12/16 18:36
 * @Version: V1.0
 */
@Slf4j
@Component
@ChannelHandler.Sharable
@AllArgsConstructor
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

	/**
	 * 连接管理
	 */
	private final ConnectManage connectManage;
	/**
	 * 队列
	 */
	private Map<String, SynchronousQueue<Object>> queueMap = new ConcurrentHashMap<>();

	@Autowired
	public NettyClientHandler(ConnectManage connectManage) {
		this.connectManage = connectManage;
	}

	/**
	 * 收到连接请求
	 *
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	@SneakyThrows
	public void channelActive(ChannelHandlerContext ctx) {
		log.info("{}已连接到RPC服务器", ctx.channel().remoteAddress());
	}

	/**
	 * 收到客户端发来消息
	 *
	 * @param ctx
	 * @param msg
	 */
	@Override
	@SneakyThrows
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		log.info("客户端收到服务器{}的消息：{}", ctx.channel().remoteAddress(), msg.toString());
		Response response = JSONUtil.toBean(msg.toString(), Response.class);
		String requestId = response.getRequestId();
		//将异步装换为同步方法，等待服务器返回数据继续执行
		SynchronousQueue<Object> queue = queueMap.get(requestId);
		queue.put(response);
		queueMap.remove(requestId);
	}

	/**
	 * 发送请求信息
	 *
	 * @param request 请求
	 * @param channel channel
	 * @return 同步阻塞队列
	 */
	public SynchronousQueue<Object> sendRequest(Request request, Channel channel) {
		SynchronousQueue<Object> queue = new SynchronousQueue<>();
		//将请求加入请求信息缓存中
		queueMap.put(request.getId(), queue);
		channel.writeAndFlush(request);
		return queue;
	}

	/**
	 * 断开连接，关闭channel
	 *
	 * @param ctx
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) {
		InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
		log.info("{}与RPC服务器断开连接.", address);
		ctx.channel().close();
		connectManage.removeChannel(ctx.channel());
	}

	/**
	 * 通信连接发生异常
	 *
	 * @param ctx
	 * @param cause
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		log.info("RPC通信服务器发生异常.{}", cause);
		ctx.channel().close();
	}
}
