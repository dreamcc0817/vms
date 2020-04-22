package com.bonc.upms.service;

/**
 * @Title: vms
 * @Package: com.bonc.upms.service
 * @Description: redis操作Service, 对象和数组都以json形式进行存储
 * @Author: dreamcc
 * @Date: 2020/4/22 19:54
 * @Version: V1.0
 */
public interface IRedisService {
	/**
	 * 存储数据
	 *
	 * @param key   key
	 * @param value value
	 */
	void set(String key, Object value);

	/**
	 * 存储数据
	 *
	 * @param key   key
	 * @param value value
	 * @param time  过期时长
	 */
	void set(String key, Object value, long time);

	/**
	 * 获取数据
	 *
	 * @param key key
	 * @return 数据
	 */
	Object get(String key);

	/**
	 * 设置超期时间
	 *
	 * @param key    key
	 * @param expire 时间
	 * @return 是否设置成功
	 */
	boolean expire(String key, long expire);

	/**
	 * 删除缓存
	 *
	 * @param key key
	 */
	void remove(String key);

	/**
	 * 自增操作
	 *
	 * @param key   key
	 * @param delta 自增步长
	 * @return 自增
	 */
	Long increment(String key, long delta);
}
