package com.dreamcc.domain.iot.service;

import cn.hutool.core.util.IdUtil;

/**
 * @author cloud-cc
 * @ClassName IdGenerator
 * @Description Id生成器
 * @date 2021/11/27 13:59
 * @Version 1.0
 */
public class IdGenerator {

    /**
     * 生成ID
     *
     * @return 雪花算法ID
     */
    public static Long snowFlakeId() {
        return IdUtil.createSnowflake(1, 1).nextId();
    }
}
