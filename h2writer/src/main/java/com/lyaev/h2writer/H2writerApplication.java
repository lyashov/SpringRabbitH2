package com.lyaev.h2writer;

import com.lyaev.h2writer.sevice.MessageService;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class H2writerApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2writerApplication.class);

    @Autowired
    private Environment env;

    @Autowired
    MessageService messageService;

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
     * Waiting for messages process. Each received messages is saved to the database.
     * @throws IOException
     * @throws TimeoutException
     */
    @Bean
    public void RecieverProcess() throws IOException, TimeoutException {
        final String QUEUE_NAME = env.getProperty("rabbit.queue");
        Connection connection = getRabbitConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        LOGGER.info(" [*] Waiting for messages...");
        final String[] message = new String[1];
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            message[0] = new String(delivery.getBody(), "UTF-8");
            messageService.saveMessage(message[0]);
            LOGGER.info("Received ".concat(message[0]));
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {

            }
        });
    }

    public static void main(String[] args) {
        SpringApplication.run(H2writerApplication.class, args);
    }

}
