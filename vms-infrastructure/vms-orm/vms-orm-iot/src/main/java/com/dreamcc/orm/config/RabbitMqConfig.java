package com.dreamcc.orm.config;

import com.dreamcc.domain.iot.domain.valueObject.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cloud-cc
 * @ClassName RabbtiMqConfig
 * @Description rabbitmq配置类
 * @date 2021/12/2 16:34
 * @Version 1.0
 */
@Slf4j
@Configuration
public class RabbitMqConfig {

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback(message -> log.error("Send message({}) to exchange({}), routingKey({}) failed: {}"
                , message.getMessage(), message.getExchange(), message.getRoutingKey(), message.getReplyText()));
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                log.error("CorrelationData({}) ack failed: {}", correlationData, cause);
            }
        });
        return rabbitTemplate;
    }

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }

    @Bean
    public TopicExchange eventExchange() {
        return new TopicExchange(MqConstants.TOPIC_EXCHANGE_EVENT, true, false);
    }

    @Bean
    public Queue driverEventQueue() {
        Map<String, Object> arguments = new HashMap<>();
        // 15秒：15 * 1000 = 15000L
        arguments.put("x-message-ttl", 15000L);
        return new Queue(MqConstants.QUEUE_DRIVER_EVENT, true, false, false, arguments);
    }

    @Bean
    Binding driverEventBinding() {
        return BindingBuilder
                .bind(driverEventQueue())
                .to(eventExchange())
                .with(MqConstants.ROUTING_DRIVER_EVENT_PREFIX + "*");
    }


}
