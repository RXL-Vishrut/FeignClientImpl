package com.example.authservice.rabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;

    @Bean
    Queue queue() {
        return new Queue("user.queue", false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("user.exchange");
    }

    @Bean
    Binding queueBinding(){
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}