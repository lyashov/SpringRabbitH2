package com.lyaev.sender.sevice;

import com.lyaev.sender.controller.MessageRestController;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class SenderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SenderService.class);

    @Autowired
    private Environment env;

    public String sendMessage(Message message) throws IOException, TimeoutException {
        LOGGER.info("Sender service started...");
        final String QUEUE_NAME = env.getProperty("rabbit.queue");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(env.getProperty("rabbit.user"));
        factory.setPassword(env.getProperty("rabbit.password"));
        factory.setHost(env.getProperty("rabbit.host"));
        factory.setPort(Integer.parseInt(env.getProperty("rabbit.port")));
        factory.setVirtualHost(env.getProperty("rabbit.virtualHost"));
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getMessage().getBytes("UTF-8"));
            LOGGER.info(" [x] Sent '" + message.getMessage() + "'");
        }
        return message == null ? "" : message.getMessage();
    }
}
