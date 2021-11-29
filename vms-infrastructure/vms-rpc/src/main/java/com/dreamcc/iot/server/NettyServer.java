package com.dreamcc.iot.server;

import com.dreamcc.iot.rpc.annotation.RPCService;
import com.dreamcc.iot.config.NettyConfig;
import com.dreamcc.iot.rpc.codec.JSONDecoder;
import com.dreamcc.iot.rpc.codec.JSONEncoder;
import com.dreamcc.iot.rpc.config.RPCConfig;
import com.dreamcc.iot.rpc.handler.RPCServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @Title: vms
 * @Package: com.dreamcc.vms.gateway.server
 * @Description: Netty服务启动监听器
 * @Author: dreamcc
 * @Date: 2019/12/6 13:50
 * @Version: V1.0
 */
@Slf4j
@Component
@AllArgsConstructor
public class NettyServer implements ApplicationContextAware, InitializingBean {

	/**
	 * Netty配置类
	 */
	private final NettyConfig nettyConfig;
	/**
	 * RPC配置类
	 */
	private final RPCConfig rpcConfig;
	/**
	 * 用于接收客户端的TCP连接
	 */
	private EventLoopGroup nettyBossGroup;
	/**
	 * 用于处理I/O相关的读写操作，或者执行系统Task、定时任务Task等
	 */
	private EventLoopGroup nettyWorkerGroup;
	/**
	 * 用于接收客户端的TCP连接
	 */
	private EventLoopGroup rpcBossGroup;
	/**
	 * 用于处理I/O相关的读写操作，或者执行系统Task、定时任务Task等
	 */
	private EventLoopGroup rpcWorkerGroup;
	/**
	 * 线程池
	 */
	private final ThreadPoolTaskExecutor executor;

	ChannelFuture future;

	@Autowired
	public NettyServer(NettyConfig nettyConfig, RPCConfig rpcConfig, ThreadPoolTaskExecutor executor) {
		this.nettyConfig = nettyConfig;
		this.rpcConfig = rpcConfig;
		this.executor = executor;
	}

	/**
	 * RPC服务接口
	 */
	private Map<String, Object> serviceMap = new ConcurrentHashMap<>();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		//获取所有RPC服务接口
		Map<String, Object> beans = applicationContext.getBeansWithAnnotation(RPCService.class);
		for (Object serviceBean : beans.values()) {
			Class<?> clazz = serviceBean.getClass();
			Class<?>[] interfaces = clazz.getInterfaces();
			for (Class<?> anInterface : interfaces) {
				String interfaceName = anInterface.getSimpleName();
				log.info("【网关】 RPC加载服务类：{}", interfaces);
				serviceMap.put(interfaceName, serviceBean);
			}
			log.info("【网关】 RPC加载服务类加载完成:{}", serviceMap);
		}
	}

	@SneakyThrows
	@Override
	public void afterPropertiesSet() {
		log.info("【网关】 会话层管理服务启动初始化！");
		initEnvriment();
	}

	/**
	 * 启动网关，初始化环境
	 *
	 * @throws InterruptedException 异常中断
	 */
	@SneakyThrows
	private void initEnvriment() {
		CountDownLatch lock = new CountDownLatch(2);
		//启动设备会话注册服务
		executor.execute(() -> {
			lock.countDown();
			startNetty();
		});
		//启动RPC服务
		executor.execute(() -> {
			lock.countDown();
			startRPC();
		});
		lock.await();
	}

	/**
	 * 启动Netty会话管理网关
	 *
	 * @return
	 */
	public ChannelFuture startNetty() {
		//创建主线程，用于接收客户端的TCP连接
		nettyBossGroup = new NioEventLoopGroup(nettyConfig.getBossThreads());
		//创建工作线程，用于处理I/O相关的读写操作，或者执行系统Task、定时任务Task等
		nettyWorkerGroup = new NioEventLoopGroup(nettyConfig.getWorkerThreads());
		//Server端启动器
		ServerBootstrap bootstrap = new ServerBootstrap()
				.group(nettyBossGroup, nettyWorkerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ServerChannelInitializer())
				.localAddress(new InetSocketAddress(nettyConfig.getPort()))
				//打印日志信息
				.handler(new LoggingHandler(LogLevel.INFO))
				//设置队列大小
				.option(ChannelOption.SO_BACKLOG, 1024)
				//两小时没有数据的通信时，TCP会发送一个活动检测的数据报文
				.childOption(ChannelOption.SO_KEEPALIVE, true);

		try {
			future = bootstrap.bind().sync();
			if (future != null && future.isSuccess()) {
				log.info("【网关】 服务器启动成功，开始监听端口: {}", nettyConfig.getPort());
			} else {
				log.error("【网关】 服务器启动失败 ......");
			}
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			log.error("【网关】 服务器启动失败：{}", e.getMessage());
			e.printStackTrace();
		} finally {
			//关闭主线程组
			nettyBossGroup.shutdownGracefully();
			//关闭工作线程组
			nettyWorkerGroup.shutdownGracefully();
		}
		return future;
	}

	/**
	 * 启动RPC
	 *
	 * @return
	 */
	public void startRPC() {
		//加载RPC服务
		RPCServerHandler handler = new RPCServerHandler(serviceMap);
		//创建主线程，用于接收客户端的TCP连接
		rpcBossGroup = new NioEventLoopGroup(rpcConfig.getBossThreads());
		//创建工作线程，用于处理I/O相关的读写操作，或者执行系统Task、定时任务Task等
		rpcWorkerGroup = new NioEventLoopGroup(rpcConfig.getWorkerThreads());
		//Server端启动器
		ServerBootstrap bootstrap = new ServerBootstrap()
				.group(rpcBossGroup, rpcWorkerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline pipeline = ch.pipeline();
						pipeline.addLast(new IdleStateHandler(0, 0, 60));
						pipeline.addLast(new JSONDecoder());
						pipeline.addLast(new JSONEncoder());
						pipeline.addLast(handler);
					}
				})
				.localAddress(new InetSocketAddress(rpcConfig.getPort()))
				//打印日志信息
				.handler(new LoggingHandler(LogLevel.INFO))
				//设置队列大小
				.option(ChannelOption.SO_BACKLOG, 1024)
				//两小时没有数据的通信时，TCP会发送一个活动检测的数据报文
				.childOption(ChannelOption.SO_KEEPALIVE, true);

		try {
			future = bootstrap.bind().sync();
			if (future != null && future.isSuccess()) {
				log.info("【RPC】 服务器启动成功，开始监听端口: {}", rpcConfig.getPort());
			} else {
				log.error("【RPC】 服务器启动失败 ......");
			}
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			log.error("【RPC】 服务器启动失败：{}", e.getMessage());
			e.printStackTrace();
		} finally {
			//关闭主线程组
			rpcBossGroup.shutdownGracefully();
			//关闭工作线程组
			rpcWorkerGroup.shutdownGracefully();
		}
	}
}
