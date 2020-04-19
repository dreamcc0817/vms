package com.bonc.common.core.util;


import com.bonc.common.core.constant.CommonConsts;
import com.bonc.common.core.constant.SecurityConsts;
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
		return restResult(null, CommonConsts.SUCCESS, CommonConsts.SUCCESS_MSG);
	}

	public static <T> R<T> ok(T data) {
		return restResult(data, CommonConsts.SUCCESS, CommonConsts.SUCCESS_MSG);
	}

	public static <T> R<T> ok(T data, String msg) {
		return restResult(data, CommonConsts.SUCCESS, msg);
	}

	public static <T> R<T> failed() {
		return restResult(null, CommonConsts.FAILED, CommonConsts.FAILED_SUCCESS);
	}

	public static <T> R<T> failed(String msg) {
		return restResult(null, CommonConsts.FAILED, msg);
	}

	public static <T> R<T> failed(T data) {
		return restResult(data, CommonConsts.FAILED, CommonConsts.FAILED_SUCCESS);
	}

	public static <T> R<T> failed(T data, String msg) {
		return restResult(data, CommonConsts.FAILED, msg);
	}

	/**
	 * 未授权返回结果
	 */
	public static <T> R<T> forbidden(T data) {
		return restResult(data, SecurityConsts.ResultCode.FORBIDDEN.getCode(), SecurityConsts.ResultCode.FORBIDDEN.getMessage());
	}
	/**
	 * 未授权返回结果
	 */
	public static <T> R<T> unauthorized(T data) {
		return restResult(data, SecurityConsts.ResultCode.UNAUTHORIZED.getCode(), SecurityConsts.ResultCode.UNAUTHORIZED.getMessage());
	}

	private static <T> R<T> restResult(T data, int code, String msg) {
		R<T> apiResult = new R<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}
}
