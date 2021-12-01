package com.ken.kafka.stream;

import com.alibaba.fastjson.JSON;
import com.ken.kafka.stream.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Ken
 * @date 2021-11-30 16:59
 * @since v1.0
 */
@SpringBootApplication
@RestController
public class StreamApplication {
    public static void main(String[] args) {
        SpringApplication.run(StreamApplication.class, args);
    }


    @Autowired
    StreamBridge streamBridge;

    private AtomicLong atomicLong = new AtomicLong();

    @GetMapping("/send")
    public String send (){
        //第一个参数为配置文件bindings下的，第二个参数为消息内容
        Person person = new Person();
        person.setId(atomicLong.incrementAndGet());
        person.setName("张三");
        streamBridge.send("producer1-out-0", JSON.toJSONString(person));
        return "ok";
    }

}
