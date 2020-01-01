package com.lyaev.h2writer.repository;

import com.lyaev.h2writer.model.MessageEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageRepositoryTest
{
    @Autowired
    MessageRepository repository;

    final String TEST_MESSAGE = "test repository message";

    @Test
    public void testRepository() {
        MessageEntity msg = new MessageEntity();
        msg.setMessage(TEST_MESSAGE);
        repository.save(msg);
        MessageEntity msgFromBase = repository.findByMessage(TEST_MESSAGE);

        Assert.assertNotNull(msgFromBase);
        Assert.assertEquals(msg.getMessage(), msgFromBase.getMessage());
    }
}