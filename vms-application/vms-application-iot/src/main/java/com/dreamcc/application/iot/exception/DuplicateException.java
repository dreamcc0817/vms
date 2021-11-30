package com.dreamcc.application.iot.exception;

import cn.hutool.core.util.StrUtil;

/**
 * @author cloud-cc
 * @ClassName DuplicateException
 * @Description 重复异常
 * @date 2021/11/30 16:56
 * @Version 1.0
 */
public class DuplicateException extends RuntimeException {
    public DuplicateException(CharSequence template, Object... params) {
        super(StrUtil.format(template, params));
    }
}