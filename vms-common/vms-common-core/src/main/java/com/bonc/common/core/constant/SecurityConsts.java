package com.bonc.common.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Title: vms
 * @Package: com.bonc.common.core.constant
 * @Description: 安全常量
 * @Author: dreamcc
 * @Date: 2020/1/5 15:18
 * @Version: V1.0
 */
public interface SecurityConsts {
	/**
	 * 标志
	 */
	String FROM = "from";

	/**
	 * 内部
	 */
	String FROM_IN = "Y";

	/**
	 * 返回结果字段
	 */
	@AllArgsConstructor
	public enum ResultCode {
		/**
		 * 操作返回结果
		 */
		SUCCESS(200, "操作成功"),
		FAILED(500, "操作失败"),
		VALIDATE_FAILED(404, "参数检验失败"),
		UNAUTHORIZED(401, "暂未登录或token已经过期"),
		FORBIDDEN(403, "没有相关权限");
		@Getter
		private int code;
		@Getter
		private String message;
	}
}
