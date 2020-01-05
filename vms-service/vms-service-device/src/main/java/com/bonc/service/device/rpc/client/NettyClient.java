package com.bonc.service.device.rpc.client;

import cn.hutool.json.JSONUtil;
import com.bonc.common.core.constant.CommonConsts;
import com.bonc.service.device.rpc.codec.JSONDecoder;
import com.bonc.service.device.rpc.codec.JSONEncoder;
import com.bonc.service.device.rpc.connection.ConnectManage;
import com.bonc.service.device.rpc.dataBridge.Request;
import com.bonc.service.device.rpc.dataBridge.Response;
import com.bonc.service.device.rpc.handler.NettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.SocketAddress;
import java.util.concurrent.SynchronousQueue;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.rpc.client
 * @Description: RPC消费端
 * @Author: dreamcc
 * @Date: 2019/12/16 18:31
 * @Version: V1.0
 */
@Slf4j
@Component
@AllArgsConstructor
public class NettyClient {
	/**
	 * 创建工作线程
	 */
	private EventLoopGroup group = new NioEventLoopGroup(1);
	/**
	 * 创建启动器
	 */
	private Bootstrap bootstrap = new Bootstrap();
	/**
	 * 客户端handler
	 */
	@Autowired
	private NettyClientHandler clientHandler;
	/**
	 * 连接管理
	 */
	@Autowired
	private ConnectManage connectManage;

	public NettyClient() {
		bootstrap.group(group).
				channel(NioSocketChannel.class).
				option(ChannelOption.TCP_NODELAY, true).
				option(ChannelOption.SO_KEEPALIVE,true).
				handler(new ChannelInitializer<SocketChannel>() {
					//创建NIOSocketChannel成功后，在进行初始化时，将它的ChannelHandler设置到ChannelPipeline中，用于处理网络IO事件
					@Override
					protected void initChannel(SocketChannel channel) throws Exception {
						ChannelPipeline pipeline = channel.pipeline();
						pipeline.addLast(new IdleStateHandler(0, 0, 30));
						pipeline.addLast(new JSONDecoder());
						pipeline.addLast(new JSONEncoder());
						pipeline.addLast("handler",clientHandler);
					}
				});
	}


	/**
	 * 根据IP端口连接服务器，返回channel
	 *
	 * @param address 连接地址
	 * @return channel信息
	 * @throws InterruptedException 中断异常
	 */
	@SneakyThrows
	public Channel doConnect(SocketAddress address) {
		log.info("{}尝试连接",address);
		ChannelFuture future = bootstrap.connect(address);
		Channel channel = future.sync().channel();
		return channel;
	}

	/**
	 * 发生Request请求
	 *
	 * @param request 请求
	 * @return
	 */
	@SneakyThrows
	public Object send(Request request) {
		Channel channel = connectManage.chooseChannel();
		if (channel != null && channel.isActive()) {
			SynchronousQueue<Object> queue = clientHandler.sendRequest(request,channel);
			//取出结果
			Object result = queue.take();
			return JSONUtil.toJsonStr(result);
		}else {
			Response response = new Response();
			response.setCode(CommonConsts.FAILED);
			log.error("未正确连接服务器，请检查配置信息！");
			response.setErrorMsg("未正确连接服务器，请检查配置信息！");
			return JSONUtil.toJsonStr(response);
		}
	}
}
