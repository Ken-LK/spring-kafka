package com.ken.kafka.stream.config;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Ken
 * @date 2021-11-30 17:00
 * @since v1.0
 */
@Configuration
public class ConsumerConfig {


    @Bean
    public Consumer<KStream<Object, String>> process1() {

        return input ->
                input.foreach((key, value) -> {
                    System.out.println("Key: " + key + " Value: " + value);
                });
    }

    @Bean
    public Function<KStream<Object, String>,KStream<?, String>> process2() {

        return input ->input;
    }

}
