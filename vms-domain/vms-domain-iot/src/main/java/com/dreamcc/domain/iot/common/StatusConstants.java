package com.dreamcc.domain.iot.common;

import lombok.Getter;

/**
 * @author cloud-cc
 * @ClassName StatusConstants
 * @Description 状态常量
 * @date 2021/11/29 21:09
 * @Version 1.0
 */
public enum StatusConstants {
    /**
     * 在线
     */
    ONLINE(0),
    /**
     * 离线
     */
    OFF_LINE(1),
    /**
     * 未知
     */
    UNKNOWN(2);

    @Getter
    private Integer code;

    StatusConstants(Integer code) {
        this.code = code;
    }
}
