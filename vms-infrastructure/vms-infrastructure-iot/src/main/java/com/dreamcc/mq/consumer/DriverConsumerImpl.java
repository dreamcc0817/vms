package com.dreamcc.mq.consumer;

import com.dreamcc.application.iot.mq.consumer.DriverConsumer;
import com.dreamcc.application.iot.mq.event.DriverEvent;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author cloud-cc
 * @ClassName DriverConsumer
 * @Description 驱动消息消费者
 * @date 2021/12/4 08:31
 * @Version 1.0
 */
@Slf4j
@Component
public class DriverConsumerImpl implements DriverConsumer {



    @RabbitHandler
    @RabbitListener(queues = "#{driverEventQueue.name}")
    public void receiveEvent(Channel channel, Message message, DriverEvent driverEvent){
        MessageProperties properties = message.getMessageProperties();
        log.debug("接收到注册事件{}",driverEvent.getContent().toString());
        try {
            channel.basicAck(properties.getDeliveryTag(), true);
        } catch (IOException e) {
            log.error("MQ异常");
        }
    }

    @Override
    public DriverEvent receiveHandShakeEvent() {
        return null;
    }

    @Override
    public DriverEvent receiveRegisterEvent() {

        return null;
    }
}
