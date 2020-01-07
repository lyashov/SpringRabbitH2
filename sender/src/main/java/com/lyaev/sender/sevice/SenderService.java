package com.lyaev.sender.sevice;

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

    /**
     * @return rabbitmq connection.
     * Сonnection parameters from the application.properties are used
     * @throws IOException
     * @throws TimeoutException
     */
    private Connection getRabbitConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(env.getProperty("rabbit.user"));
        factory.setPassword(env.getProperty("rabbit.password"));
        factory.setHost(env.getProperty("rabbit.host"));
        factory.setPort(Integer.parseInt(env.getProperty("rabbit.port")));
        factory.setVirtualHost(env.getProperty("rabbit.virtualHost"));
        Connection connection = factory.newConnection();
        return connection;
    }

    /**
     * Send message to rabbitmq
     * @throws IOException
     * @throws TimeoutException
     */
    public String sendMessage(Message message) throws IOException, TimeoutException {
        LOGGER.info("Sending message...");
        if (message == null) return "";
        final String QUEUE_NAME = env.getProperty("rabbit.queue");
        Connection connection = getRabbitConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicPublish("", QUEUE_NAME, null, message.getMessage().getBytes("UTF-8"));
        LOGGER.info("[x] Sent '" + message.getMessage() + "'");
        return message.getMessage();
    }
}
