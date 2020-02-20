package com.mygit.salesreport.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConfig {

    @Bean
    public Queue filesQueue(){
        return new Queue("filesQueue", false);
    }
}
