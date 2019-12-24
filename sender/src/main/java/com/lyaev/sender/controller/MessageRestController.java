package com.lyaev.sender.controller;

import com.lyaev.sender.sevice.Message;
import com.lyaev.sender.sevice.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class MessageRestController {
    @Autowired
    SenderService senderService;

    @RequestMapping({"/api/message"})
    public ResponseEntity<Message> postMessage(@RequestBody Message message) throws IOException, TimeoutException {
        if (message != null) {
            senderService.sendMessage(message);
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        }
        return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
    }
}
