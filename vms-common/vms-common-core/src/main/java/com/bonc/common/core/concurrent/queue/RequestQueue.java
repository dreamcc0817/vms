package com.bonc.common.core.concurrent.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title: vms
 * @Package: com.bonc.common.core.concurrent
 * @Description: 请求内存队列
 * @Author: dreamcc
 * @Date: 2019/12/10 13:55
 * @Version: V1.0
 */
public class RequestQueue {

	/**
	 * 内存队列
	 */
	private List<ArrayBlockingQueue<IoTRequest>> queues = new ArrayList<>();
	/**
	 * 标识位map
	 */
	private Map<Integer, Boolean> flagMap = new ConcurrentHashMap<>();

	/**
	 * 绝对线程安全的一种方式
	 * 静态内部类去初始化单例
	 */
	private static class Singleton {
		private static RequestQueue instance;

		static {
			instance = new RequestQueue();
		}

		public static RequestQueue getInstance() {
			return instance;
		}
	}

	/**
	 * jvm的机制去保证多线程并发安全
	 * 内部类的初始化，一定只会发生一次，不管多少线程去并发初始化
	 *
	 * @return 单例实例
	 */
	public static RequestQueue getInstance() {
		return Singleton.getInstance();
	}

	/**
	 * 添加一个内存队列
	 *
	 * @param queue 请求
	 */
	public void addQueue(ArrayBlockingQueue<IoTRequest> queue) {
		this.queues.add(queue);
	}

	/**
	 * 获取内存队列的数量
	 *
	 * @return 内存队列的数量
	 */
	public int queueSize() {
		return this.queues.size();
	}

	/**
	 * 获取内存队列
	 *
	 * @param index 队列位置
	 * @return 请求
	 */
	public ArrayBlockingQueue<IoTRequest> getQueue(int index) {
		return queues.get(index);
	}
}
