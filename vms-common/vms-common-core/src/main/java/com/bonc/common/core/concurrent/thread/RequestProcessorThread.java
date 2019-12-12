package com.bonc.common.core.concurrent.thread;

import com.bonc.common.core.concurrent.queue.IoTRequest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * @Title: vms
 * @Package: com.bonc.common.core.concurrent.thread
 * @Description: 执行请求的工作线程
 * @Author: dreamcc
 * @Date: 2019/12/10 14:59
 * @Version: V1.0
 */
public class RequestProcessorThread implements Callable<Boolean> {

	/**
	 * 监控自己的内存队列
	 */
	private ArrayBlockingQueue<IoTRequest> queue;

	public RequestProcessorThread(ArrayBlockingQueue<IoTRequest> queue) {
		this.queue = queue;
	}


	@Override
	public Boolean call() throws Exception {
		try {
			while (true) {

			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return true;
	}
}
