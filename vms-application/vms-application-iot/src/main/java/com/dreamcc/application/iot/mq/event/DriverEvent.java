package com.dreamcc.application.iot.mq.event;

import lombok.Data;

/**
 * @author cloud-cc
 * @ClassName DriverEvent
 * @Description 驱动消息
 * @date 2021/12/2 14:21
 * @Version 1.0
 */
@Data
public class DriverEvent {

    /**
     * Driver Event
     */
    private String type;

    private Object content;

    public DriverEvent(String type, Object content) {
        this.type = type;
        this.content = content;
    }
}
