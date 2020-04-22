package com.bonc.upms.service.impl;

import com.bonc.upms.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Title: vms
 * @Package: com.bonc.upms.service.impl
 * @Description: redis操作实现类
 * @Author: dreamcc
 * @Date: 2020/4/22 20:02
 * @Version: V1.0
 */
@Service
public class RedisServiceImpl implements IRedisService {

	@Autowired
	private RedisTemplate<String,Object> redisTemplate;

	/**
	 * 设置缓存
	 *
	 * @param key   key
	 * @param value value
	 */
	@Override
	public void set(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 设置缓存
	 *
	 * @param key   key
	 * @param value value
	 * @param time  过期时长
	 */
	@Override
	public void set(String key, Object value, long time) {
		redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
	}

	/**
	 * 获取值
	 *
	 * @param key key
	 * @return 值
	 */
	@Override
	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * 设置过期时间
	 *
	 * @param key    key
	 * @param expire 时间
	 * @return 是否设置成功
	 */
	@Override
	public boolean expire(String key, long expire) {
		return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
	}

	/**
	 * 删除缓存
	 *
	 * @param key key
	 */
	@Override
	public void remove(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 增长key
	 *
	 * @param key   key
	 * @param delta 自增步长
	 * @return 自增之后值
	 */
	@Override
	public Long increment(String key, long delta) {
		return redisTemplate.opsForValue().increment(key, delta);
	}
}
