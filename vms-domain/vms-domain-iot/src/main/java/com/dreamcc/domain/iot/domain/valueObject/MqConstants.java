package com.dreamcc.domain.iot.domain.valueObject;

/**
 * @author cloud-cc
 * @ClassName MqConstants
 * @Description MQ常量
 * @date 2021/12/2 17:01
 * @Version 1.0
 */
public interface MqConstants {

    String TOPIC_EXCHANGE_EVENT = "vms.exchange.event";
    String ROUTING_DRIVER_EVENT_PREFIX = "vms.routing.event.driver.";
    String QUEUE_DRIVER_EVENT = "vms.queue.event.driver";
}
