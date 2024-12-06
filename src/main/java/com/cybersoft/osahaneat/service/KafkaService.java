package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.service.imp.KafkaImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService implements KafkaImp {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Override
    public void sendMessage(String message) {
        this.kafkaTemplate.send(TOPIC, message);
    }

    @Override
    @KafkaListener(topics = "maianhtrung", groupId = "my-consumer-group")
    public void listen(String message) {
        System.out.println(message);

    }
}
