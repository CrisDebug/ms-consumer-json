package com.alertas.ms_alertas.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class RabbitConfig {

    // public static final String QUEUE = "alertas.queue";
    public static final String QUEUE = "alertas.queue.json";
    public static final String EXCHANGE = "alertas.exchange";
    public static final String ROUTING_KEY = "alertas.routingkey";

    // prueba
    @PostConstruct
    public void init() {
        System.out.println(">>> CONSUMER JSON INICIADO <<<");
    }

    @PostConstruct
    public void debug() {
        System.out.println("RABBIT CONFIG JSON CARGADA");
    }

    @Bean
    Queue queue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

    // 🔥 ESTE ES EL FIX CLAVE
    @Bean
    public org.springframework.amqp.support.converter.MessageConverter jsonMessageConverter() {
        return new org.springframework.amqp.support.converter.Jackson2JsonMessageConverter();
    }

    @Bean
    public org.springframework.amqp.rabbit.core.RabbitTemplate rabbitTemplate(
            org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {

        org.springframework.amqp.rabbit.core.RabbitTemplate template = new org.springframework.amqp.rabbit.core.RabbitTemplate(
                connectionFactory);

        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public org.springframework.amqp.rabbit.core.RabbitAdmin rabbitAdmin(
            org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {
        return new org.springframework.amqp.rabbit.core.RabbitAdmin(connectionFactory);
    }
}