package br.com.catolica.ms_email.config;

import lombok.Getter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class RabbitMQConfig {
    @Value("${spring.rabbitmq.queue.name}")
    private String emailQueue;

    @Value("${spring.rabbitmq.exchange.name}")
    private String emailExchangeName;

    @Value("${spring.rabbitmq.routing-key.name}")
    private String emailRoutingKey;

    @Bean
    public Queue emailQueue() {
        return new Queue(emailQueue, true, false, false);
    }


    @Bean
    public TopicExchange emailExchange() {
        return new TopicExchange(emailExchangeName, true, false);
    }

    @Bean
    public Binding bindRegisterQueue() {
        return BindingBuilder
                .bind(emailQueue())
                .to(emailExchange())
                .with(emailRoutingKey);
    }


    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}

