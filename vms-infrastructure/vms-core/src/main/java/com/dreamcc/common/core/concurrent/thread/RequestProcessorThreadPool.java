package com.dreamcc.common.core.concurrent.thread;

import com.dreamcc.common.core.concurrent.queue.IoTRequest;
import com.dreamcc.common.core.concurrent.queue.RequestQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Title: vms
 * @Package: com.dreamcc.common.core.concurrent.thread
 * @Description: 请求处理线程池：单例
 * @Author: dreamcc
 * @Date: 2019/12/10 14:17
 * @Version: V1.0
 */
@Configuration
@PropertySource("classpath:thread.properties")
public class RequestProcessorThreadPool {
	/**
	 * 线程池基本大小
	 */
	@Value("${iot.thread.coreThread}")
	private int corePoolSize;
	/**
	 * 线程池最大数量
	 */
	@Value("${iot.thread.maxThread}")
	private int maximumPoolSize;
	/**
	 * 线程活动保持时间
	 */
	@Value("${iot.thread.keepAliveTime}")
	private long keepAliveTime;
	/**
	 * 任务队列容量
	 */
	@Value("${iot.thread.capacitySize}")
	private int capacitySize;
	/**
	 * 自定义线程池
	 */
	private ExecutorService threadPool = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime, TimeUnit.SECONDS,new ArrayBlockingQueue<>(capacitySize),new ThreadPoolExecutor.AbortPolicy());

	public RequestProcessorThreadPool(){
		RequestQueue requestQueue = RequestQueue.getInstance();
		for (int i = 0; i < corePoolSize; i++) {
			ArrayBlockingQueue<IoTRequest> queue = new ArrayBlockingQueue<>(capacitySize);
			requestQueue.addQueue(queue);
			threadPool.submit(new com.dreamcc.common.core.concurrent.thread.RequestProcessorThread(queue));
		}
	}

	private static class Singleton {

		private static RequestProcessorThreadPool instance;

		static {
			instance = new RequestProcessorThreadPool();
		}

		public static RequestProcessorThreadPool getInstance() {
			return instance;
		}

	}
}
