package com.lyaev.sender.sevice;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest
class SenderServiceTest {
    @Autowired
    SenderService senderService;
    @Autowired
    Message message;
    final String TEST_MESSAGE = "test message";

    @Test
    void sendMessage() throws IOException, TimeoutException {
        message.setMessage(TEST_MESSAGE);
        org.junit.Assert.assertTrue(senderService.sendMessage(message).equals(TEST_MESSAGE));
    }
}