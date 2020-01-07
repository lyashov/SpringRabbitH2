package com.lyaev.h2writer.sevice;

import com.lyaev.h2writer.model.MessageEntity;
import com.lyaev.h2writer.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    MessageRepository repository;

    /**
     * Save message to database.
     * @param message
     * @return message's entity
     */
    public MessageEntity saveMessage(String message) {
        MessageEntity messageEntity = new MessageEntity(message);
        return repository.save(messageEntity);
    }

    /**
     * Find message in database (this method is used for tests)
     * @param message
     * @return message's entity
     */
    public MessageEntity findByMessage(String message) {
        return repository.findByMessage(message);
    }
}
