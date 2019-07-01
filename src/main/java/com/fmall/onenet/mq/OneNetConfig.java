package com.fmall.onenet.mq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OneNetConfig {
    @Bean
    public MqClient mqClient(){
        MqClient mqClient = new MqClient();
        mqClient.connect();
        return mqClient;
    }


    @Bean
    public Execute execute(){
        Execute e = new Execute();
        e.start();
        return e;
    }
}
