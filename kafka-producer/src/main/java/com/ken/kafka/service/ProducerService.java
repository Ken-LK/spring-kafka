package com.ken.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author Ken
 * @date 2021-11-29 15:27
 * @since v1.0
 */
@Service
public class ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, Object o) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic,0,"ssss", o);
        future.addCallback(result -> logger.info("生产者成功发送消息到topic:{} partition:{}的消息",
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition()),
                        ex -> logger.error("生产者发送消失败，原因：{}", ex.getMessage()));

    }

    public void sendMsgByPartition(String topic, int partition, Object o) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic,partition,"sss", o);
        future.addCallback(result -> logger.info("生产者成功发送消息到topic:{} partition:{}的消息",
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition()),
                ex -> logger.error("生产者发送消失败，原因：{}",
                        ex.getMessage()));
    }
}
