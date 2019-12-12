package com.bonc.common.core.util;


import com.bonc.common.core.constant.CommonConstants;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Title: sea-heart
 * @Package: com.bonc.common.core.util
 * @Description: 响应信息主体
 * @Author: dreamcc
 * @Date: 2019/7/26 9:36
 * @Version: V1.0
 */
@Builder
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class R<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private int code;

	@Getter
	@Setter
	private String msg;

	@Getter
	@Setter
	private T data;

	public static <T> R<T> ok() {
		return restResult(null, CommonConstants.SUCCESS, CommonConstants.SUCCESS_MSG);
	}

	public static <T> R<T> ok(T data) {
		return restResult(data, CommonConstants.SUCCESS, CommonConstants.SUCCESS_MSG);
	}

	public static <T> R<T> ok(T data, String msg) {
		return restResult(data, CommonConstants.SUCCESS, msg);
	}

	public static <T> R<T> failed() {
		return restResult(null, CommonConstants.FAILED, CommonConstants.FAILED_SUCCESS);
	}

	public static <T> R<T> failed(String msg) {
		return restResult(null, CommonConstants.FAILED, msg);
	}

	public static <T> R<T> failed(T data) {
		return restResult(data, CommonConstants.FAILED, CommonConstants.FAILED_SUCCESS);
	}

	public static <T> R<T> failed(T data, String msg) {
		return restResult(data, CommonConstants.FAILED, msg);
	}

	private static <T> R<T> restResult(T data, int code, String msg) {
		R<T> apiResult = new R<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}
}
