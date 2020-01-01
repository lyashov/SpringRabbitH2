package com.lyaev.h2writer.repository;

import com.lyaev.h2writer.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    MessageEntity findByMessage(String message);
}
