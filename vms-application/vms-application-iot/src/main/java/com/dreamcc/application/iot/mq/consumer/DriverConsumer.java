package com.dreamcc.application.iot.mq.consumer;

import com.dreamcc.application.iot.mq.event.DriverEvent;

/**
 * @author cloud-cc
 * @ClassName DriverConsumer
 * @Description 驱动事件消费者
 * @date 2021/12/2 14:44
 * @Version 1.0
 */
public interface DriverConsumer {

    /**
     * 发送握手命令
     *
     * @return DriverEvent
     */
    DriverEvent receiveHandShakeEvent();

    /**
     * 接收注册事件
     *
     * @return DriverEvent
     */
    DriverEvent receiveRegisterEvent();
}
