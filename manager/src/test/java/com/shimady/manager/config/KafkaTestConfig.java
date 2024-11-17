package com.shimady.manager.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;

import java.util.List;

@TestConfiguration
public class KafkaTestConfig {
    @Bean
    public Consumer<String, Object> consumer(ConsumerFactory<?, ?> factory) {
        var consumer = factory.createConsumer();
        consumer.subscribe(List.of("student", "discipline"));
        return (Consumer<String, Object>) consumer;
    }

    @Bean
    public NewTopic studentTopic() {
        return TopicBuilder
                .name("student")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic disciplineTopic() {
        return TopicBuilder
                .name("discipline")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
