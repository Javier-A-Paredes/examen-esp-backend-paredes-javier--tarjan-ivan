package com.dh.catalog.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "backendExchange";
    public static final String TOPIC_SAVE_MOVIE = "com.dh.backend.saveMovie";
    public static final String QUEUE_SAVE_MOVIE ="queueSaveMovie";

    public static final String TOPIC_SAVE_SERIE = "com.dh.backend.saveSerie";
    public static final String QUEUE_SAVE_SERIE ="queueSaveSerie";

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queueSaveMovie(){
        return new Queue(QUEUE_SAVE_MOVIE);
    }

    @Bean
    public Binding declareBindingSpecificSaveMovie(){
        return BindingBuilder.bind(queueSaveMovie()).to(appExchange()).with(TOPIC_SAVE_MOVIE);
    }

    @Bean
    public Queue queueSaveSerie(){
        return new Queue(QUEUE_SAVE_SERIE);
    }

    @Bean
    public Binding declareBindingSpecificSaveSerie(){
        return BindingBuilder.bind(queueSaveSerie()).to(appExchange()).with(TOPIC_SAVE_SERIE);
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
