package com.dreamcc.application.iot.mq.producer;

import com.dreamcc.application.iot.mq.event.DriverEvent;

/**
 * @author cloud-cc
 * @ClassName DriverProducer
 * @Description 驱动事件生产者
 * @date 2021/12/2 14:13
 * @Version 1.0
 */
public interface DriverProducer {

    /**
     * 驱动事件发送
     *
     * @param driverEvent 消息
     */
    void driverEventSender(DriverEvent driverEvent);
}
