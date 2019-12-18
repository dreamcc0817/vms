package com.bonc.service.device.rpc.dataBridge;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: vms
 * @Package: com.bonc.service.device.rpc.dataBridge
 * @Description: 返回信息
 * @Author: dreamcc
 * @Date: 2019/12/16 18:00
 * @Version: V1.0
 */
@Data
public class Response implements Serializable {

	private static final long serialVersionUID = 4852847675718142511L;
	/**
	 * 请求ID
	 */
	private String requestId;
	/**
	 * 响应码
	 */
	private int code;
	/**
	 * 返回消息
	 */
	private Object data;
	/**
	 * 错误信息
	 */
	private String errorMsg;
}