package com.dreamcc.domain.iot.service;

import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Component;

/**
 * @author cloud-cc
 * @ClassName IdGenerator
 * @Description Id生成器
 * @date 2021/11/27 13:59
 * @Version 1.0
 */
@Component
public class IdGenerator {

    /**
     * 生成ID
     *
     * @return 雪花算法ID
     */
    public long snowFlakeId() {
        return IdUtil.createSnowflake(1, 1).nextId();
    }
}
