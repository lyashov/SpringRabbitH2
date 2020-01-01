package com.lyaev.h2writer.sevice;

import com.lyaev.h2writer.model.MessageEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {
    @Autowired
    MessageService messageService;
    final String TEST_MESSAGE = "test message";
    @Test
    public void saveMessage() {
        MessageEntity message = messageService.saveMessage(TEST_MESSAGE);
        org.junit.Assert.assertTrue(message.getMessage().equals(TEST_MESSAGE));
    }

    @Test
    public void findByMessage() {
        messageService.saveMessage(TEST_MESSAGE);
        MessageEntity message = messageService.findByMessage(TEST_MESSAGE);
        org.junit.Assert.assertTrue(message.getMessage().equals(TEST_MESSAGE));
    }
}