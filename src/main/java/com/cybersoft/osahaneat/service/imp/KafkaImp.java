package com.cybersoft.osahaneat.service.imp;

public interface KafkaImp {
    static final String TOPIC = "maianhtrung";
    public void sendMessage(String message);

    public void listen(String message);
}
