package com.lyaev.h2writer.sevice;

import com.lyaev.h2writer.model.MessageEntity;
import com.lyaev.h2writer.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    MessageRepository repository;

    public MessageEntity saveMessage(String message) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessage(message);
        return repository.save(messageEntity);
    }
}
