package com.dreamcc.iot.entity;

import io.netty.channel.ChannelId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title: vms
 * @Package: com.dreamcc.vms.gateway.entity
 * @Description: 物理网全局信息
 * @Author: dreamcc
 * @Date: 2020/1/5 19:12
 * @Version: V1.0
 */
public class GlobalInfo {

	/**
	 * 缓冲Rediskey
	 */
	public static String GLOBAL_IOT_REDIS_KEY;
	/**
	 * IOT信息 物联网设备映射
	 */
	public static Map<String, IotInfo> iotMapper;
	/**
	 * 全局netty Channel远程服务单元管理器
	 */
	public static final Map<ChannelId, RTUChannelInfo> CHANNEL_INFO_MAP = new ConcurrentHashMap<>();
	/**
	 * 全局netty Channel SN码管理器
	 */
	public static final Map<String, RTUChannelInfo> SN_CHANNEL_INFO_MAP = new ConcurrentHashMap<>();
}
