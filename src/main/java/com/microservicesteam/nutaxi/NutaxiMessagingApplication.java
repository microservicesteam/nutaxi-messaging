package com.microservicesteam.nutaxi;

import static org.springframework.cloud.stream.messaging.Source.OUTPUT;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

@SpringBootApplication
@EnableBinding(Source.class)
public class NutaxiMessagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(NutaxiMessagingApplication.class, args);
    }

    @Bean
    @InboundChannelAdapter(value = OUTPUT)
    public MessageSource<String> timerMessageSource() {
        return () -> new GenericMessage<>(getMessage());
    }

    private static String getMessage() {
        return "Hello Nutaxi Kafka Stream user with id: " + new Random().nextInt();
    }

}
