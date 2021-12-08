package com.dreamcc.mq.producer;

import cn.hutool.json.JSONUtil;
import com.dreamcc.application.iot.mq.event.DriverEvent;
import com.dreamcc.application.iot.mq.producer.DriverProducer;
import com.dreamcc.domain.iot.common.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author cloud-cc
 * @ClassName DriverProducerImpl
 * @Description RabbitMQ实现消息发送
 * @date 2021/12/2 17:24
 * @Version 1.0
 */
@Slf4j
@Component
public class DriverProducerImpl implements DriverProducer {

    private final RabbitTemplate rabbitTemplate;

    public DriverProducerImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void driverEventSender(DriverEvent driverEvent) {
        log.debug("send driver event:{}", JSONUtil.toJsonStr(driverEvent));
        rabbitTemplate.convertAndSend(MqConstants.TOPIC_EXCHANGE_EVENT
                ,MqConstants.ROUTING_DRIVER_EVENT_PREFIX
                ,driverEvent);
    }
}
