package com.ken.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ken.kafka.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

/**
 * @author Ken
 * @date 2021-11-29 16:09
 * @since v1.0
 */
@Service
@Slf4j
public class ConsumerService {

    @Value("${kafka.topic.my-topic1}")
    private String myTopic1;
    @Value("${kafka.topic.my-topic2}")
    private String myTopic2;
    @Value("${kafka.topic.my-topic3}")
    private String myTopic3;
    private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();


    @KafkaListener(groupId = "group1",containerFactory="batchFactory", topicPartitions = {
            @TopicPartition(topic = "${kafka.topic.my-topic1}", partitions = {"0"})})
    public void consumeMessage(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
        log.info("接收到消息数量：{}",records.size());
        records.forEach(System.out :: println);
        // 确认消费
        ack.acknowledge();
    }

//    @KafkaListener(groupId = "group14", topicPartitions = {
//            @TopicPartition(topic = "${kafka.topic.my-topic1}", partitions = {"0"})
//    })
//    public void consumeMessage11(ConsumerRecord<String, String> consumerRecord) {
//        try {
//            Message message = objectMapper.readValue(consumerRecord.value(), Message.class);
//            logger.info("消费者消费topic:{} partition:{}的消息 -> {}", consumerRecord.topic(),
//                    consumerRecord.partition(),
//                    consumerRecord.key());
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }

//    @KafkaListener(topics = {"${kafka.topic.my-topic2}"}, groupId = "group2")
//    public void consumeMessage2(ConsumerRecord<String, String> consumerRecord) {
//        logger.info("消费者消费{}的消息 -> {}", myTopic2, consumerRecord.toString());
//    }
//
//    @KafkaListener(topics = {"${kafka.topic.my-topic3}"}, groupId = "group3")
//    public void consumeMessage3(ConsumerRecord<String, String> consumerRecord) {
//        logger.info("消费者消费{}的消息 -> {}", myTopic3, consumerRecord.toString());
//    }


}
