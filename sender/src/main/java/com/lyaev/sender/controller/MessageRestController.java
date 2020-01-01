package com.lyaev.sender.controller;

import com.lyaev.sender.sevice.Message;
import com.lyaev.sender.sevice.SenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    Message msg;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageRestController.class);

    @RequestMapping("/")
    public @ResponseBody ResponseEntity<Message> mainPage() {
        msg.setMessage("This service has API at: http://hostname/api/message");
        return new ResponseEntity<Message>(msg, HttpStatus.OK);
    }

    @RequestMapping({"/api/message"})
    public @ResponseBody ResponseEntity<Message> postMessage(@RequestBody Message message) throws IOException, TimeoutException {
        LOGGER.info("Run controller /api/message");
        if (message != null) {
            senderService.sendMessage(message);
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        }
        return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
    }
}
