package com.dreamcc.iot.cache;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title: vms
 * @Package: com.dreamcc.vms.gateway.cache
 * @Description: 简单实现缓存类，用于保存所有连接到网关的终端"ip：channel"信息
 * @Author: dreamcc
 * @Date: 2019/12/10 15:21
 * @Version: V1.0
 */
@Slf4j
public class IoTDeviceCache {

	/**
	 * 会话信息
	 */
	private static List<ConcurrentHashMap<String, Channel>> deviceCache = new ArrayList<>();
	/**
	 * ConcurrentHashMap缓存数量
	 */
	private static int deviceCount = 100000;
	/**
	 * 初始化缓存容量
	 */
	private static int scale = 10;

	static {
		initClientChannelCache();
		log.info("【网关】 初始化IoTDeviceCache缓存完成......");
	}

	public static void initClientChannelCache() {
		for (int i = 0; i < scale; i++) {
			deviceCache.add(new ConcurrentHashMap<>(deviceCount));
		}
	}

	/**
	 * 根据key  获取缓存数据的ConcurrentHashMap实体
	 *
	 * @param key
	 * @return
	 */
	private static ConcurrentHashMap<String, Channel> getCacheInstance(String key) {
		int hashCode = key.hashCode();
		hashCode = (hashCode < 0) ? -hashCode : hashCode;
		int index = hashCode % scale;
		return deviceCache.get(index);
	}

	/**
	 * 新增会话信息
	 *
	 * @param key
	 * @param value
	 */
	public static void set(String key, Channel value) {
		log.info("【网关】 新增会话信息......");
		getCacheInstance(key).put(key, value);
	}

	/**
	 * 获取会话信息
	 *
	 * @param key
	 */
	public static Channel get(String key) {
		log.info("【网关】 获取会话信息......");
		return getCacheInstance(key).get(key);

	}

	/**
	 * 删除会话信息
	 *
	 * @param key
	 */
	public static void removeOne(String key) {
		log.info("【网关】 删除会话信息......");
		Channel channel = getCacheInstance(key).remove(key);
		channel.closeFuture();
	}

	/**
	 * 清空所有缓存
	 */
	public static void clearAll() {
		log.info("【网关】 删除所有会话信息......");
		for (ConcurrentHashMap<String, Channel> concurrentHashMap : deviceCache) {
			concurrentHashMap.clear();
		}
	}
}
