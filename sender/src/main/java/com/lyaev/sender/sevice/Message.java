package com.lyaev.sender.sevice;

public class Message {
    private String message;

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
