package com.ken.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Ken
 * @date 2021-11-30 10:29
 * @since v1.0
 */
@Configuration
@RequiredArgsConstructor
public class TopicManage {
    private final TopicConfig topicConfig;

    private final GenericWebApplicationContext context;


    @PostConstruct
    public void init() {
        initializeBeans(topicConfig.getTopics());
    }

    private void initializeBeans(List<TopicConfig.Topic> topics) {
        topics.forEach(t -> context.registerBean(t.name, NewTopic.class, t::toNewTopic));
    }

}
