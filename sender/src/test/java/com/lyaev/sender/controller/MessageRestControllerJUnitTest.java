package com.lyaev.sender.controller;

import com.lyaev.sender.sevice.Message;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessageRestControllerJUnitTest {
    @Autowired
    private MessageRestController controller;

    @Autowired
    Message msg;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void messageRestControllerSmokeTest() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void greetengControllerTest() throws Exception {
        String helloMessage = "{\"message\":\"This service has API at: http://hostname/api/message\"}";
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains(helloMessage);
    }
}