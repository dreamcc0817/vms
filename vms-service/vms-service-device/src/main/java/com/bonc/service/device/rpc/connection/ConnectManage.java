package com.bonc.service.device.rpc.connection;

import com.bonc.service.device.rpc.client.NettyClient;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOutboundInvoker;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.rpc.client
 * @Description: 连接管理类
 * @Author: dreamcc
 * @Date: 2019/12/16 18:40
 * @Version: V1.0
 */
@Slf4j
@Component
@AllArgsConstructor
public class ConnectManage {

	/**
	 * 消费客户端
	 */
	private final NettyClient nettyClient;

	/**
	 * 自动负载
	 */
	private AtomicInteger roundRobin = new AtomicInteger(0);
	/**
	 * 频道列表
	 */
	private CopyOnWriteArrayList<Channel> channels = new CopyOnWriteArrayList<>();
	/**
	 * 节点信息
	 */
	private Map<SocketAddress, Channel> channelNodes = new ConcurrentHashMap<>();

	@Autowired
	public ConnectManage(NettyClient nettyClient) {
		this.nettyClient = nettyClient;
	}

	/**
	 * 选择Channel
	 *
	 * @return channel信息
	 */
	public Channel chooseChannel() {
		if (channels.size() > 0) {
			int size = channels.size();
			int index = (roundRobin.getAndAdd(1) + size) % size;
			return channels.get(index);
		}
		log.error("暂无channel信息");
		return null;
	}


	/**
	 * 更新连接信息
	 *
	 * @param addressList 地址列表
	 */
	public synchronized void updateConnectServer(List<String> addressList) {
		if (addressList.size() == 0) {
			log.error("没有节点可供使用，全部服务器节点已经关闭。");
			//关闭channel
			channels.stream().map(Channel::remoteAddress).map(remotePeer -> channelNodes.get(remotePeer)).forEach(ChannelOutboundInvoker::close);
			channels.clear();
			channelNodes.clear();
			return;
		}
		Set<SocketAddress> newAllServerNodeSet = new HashSet<>();
		//重新加载服务器地址信息
		addressList.stream().map(s -> s.split(":")).filter(array -> array.length == 2).forEach(array -> {
			String host = array[0];
			int port = Integer.parseInt(array[1]);
			final SocketAddress remotePeer = new InetSocketAddress(host, port);
			newAllServerNodeSet.add(remotePeer);
		});
		//重新连接服务节点
		newAllServerNodeSet.forEach(serverNodeAddress -> {
			Channel channel = channelNodes.get(serverNodeAddress);
			if (channel != null && channel.isOpen()) {
				log.info("当前服务节点已存在,无需重新连接.{}", serverNodeAddress);
			} else {
				connectServerNode(serverNodeAddress);
			}
		});

	}

	/**
	 * 连接服务器
	 *
	 * @param address ip地址
	 */
	@SneakyThrows
	private void connectServerNode(SocketAddress address) {
		try {
			Channel channel = nettyClient.doConnect(address);
			addChannel(channel, address);
		} catch (Exception e) {
			log.info("未能成功连接到服务器:{}", address);
		}
	}

	/**
	 * 添加channel
	 *
	 * @param channel channel
	 * @param address ip地址
	 */
	private void addChannel(Channel channel, SocketAddress address) {
		log.info("加入Channel到连接管理器.{}", address);
		channels.add(channel);
		channelNodes.put(address, channel);
	}

	/**
	 * 移除失效channel
	 *
	 * @param channel 失效channel
	 */
	public void removeChannel(Channel channel) {
		log.info("从连接管理器中移除失效Channel.{}", channel.remoteAddress());
		SocketAddress remotePeer = channel.remoteAddress();
		channelNodes.remove(remotePeer);
		channels.remove(channel);
	}
}
