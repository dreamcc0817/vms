package com.dreamcc.orm.mq.producer;

import com.dreamcc.application.iot.mq.event.DriverEvent;
import com.dreamcc.application.iot.mq.producer.DriverProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author cloud-cc
 * @ClassName DriverProducerImpl
 * @Description TODO
 * @date 2021/12/2 17:24
 * @Version 1.0
 */
@Service
public class DriverProducerImpl implements DriverProducer {

    private final RabbitTemplate rabbitTemplate;

    public DriverProducerImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void driverEventSender(DriverEvent driverEvent) {
        rabbitTemplate.convertAndSend(driverEvent);
    }
}
