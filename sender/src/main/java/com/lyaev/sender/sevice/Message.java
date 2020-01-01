package com.lyaev.sender.sevice;

import org.springframework.stereotype.Component;

@Component
public class Message {
    private String message;

    public Message(String message) {
        this.message = message;
    }

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "message='" + message + '\'' +
                '}';
    }
}
