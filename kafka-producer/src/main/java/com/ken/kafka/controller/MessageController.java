package com.ken.kafka.controller;

import com.ken.kafka.entity.Message;
import com.ken.kafka.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Ken
 * @date 2021-11-29 15:31
 * @since v1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/msg")
public class MessageController {

    private final ProducerService producer;

    @Value("${kafka.topic.my-topic1}")
    String myTopic1;
    @Value("${kafka.topic.my-topic2}")
    String myTopic2;
    @Value("${kafka.topic.my-topic3}")
    String myTopic3;

    private AtomicLong atomicLong = new AtomicLong();

    @GetMapping("/{info}")
    public void sendMessageToKafkaTopic(@PathVariable("info") String info) {
        this.producer.sendMsgByPartition(myTopic1,0, new Message(atomicLong.addAndGet(1), info));
//        this.producer.sendMessage(myTopic1, new Message(atomicLong.addAndGet(1), info));
        this.producer.sendMessage(myTopic2, new Message(atomicLong.addAndGet(1), info));
        this.producer.sendMessage(myTopic3, new Message(atomicLong.addAndGet(1), info));
    }


}
