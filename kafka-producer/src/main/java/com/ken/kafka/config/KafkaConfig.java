package com.ken.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

/**
 * @author Ken
 */
@Configuration
public class KafkaConfig {

    @Value("${kafka.topic.my-topic1}")
    String myTopic1;
    @Value("${kafka.topic.my-topic2}")
    String myTopic2;
    @Value("${kafka.topic.my-topic3}")
    String myTopic3;

    /**
     * JSON消息转换器
     */
    @Bean
    public RecordMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }

    /**
     * 通过注入一个 NewTopic 类型的 Bean 来创建 topic，如果 topic 已存在，则会忽略。
     */
    @Bean
    public NewTopic myTopic() {
        return new NewTopic(myTopic1, 2, (short) 1);
    }

    @Bean
    public NewTopic myTopic2() {
        return new NewTopic(myTopic2, 1, (short) 1);
    }

    @Bean
    public NewTopic myTopic3() {
        return new NewTopic(myTopic3, 3, (short) 1);
    }
}
