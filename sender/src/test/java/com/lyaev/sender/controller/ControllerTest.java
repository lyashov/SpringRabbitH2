package com.lyaev.sender.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lyaev.sender.sevice.Message;
import com.lyaev.sender.sevice.SenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebMvcTest(MessageRestController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SenderService service;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {



        given(service.sendMessage("gg")).willReturn(allEmployees);

        mvc.perform(get("/api/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
      //  when(service.sendMessage(new Message("test message"))).thenReturn("test message");
        //this.mockMvc.perform(get("/api/message")).andDo(print()).andExpect(status().isOk())
          //      .andExpect(content().string(containsString("test message")));

       // this.mockMvc.perform(get("/api/message")).andDo(print()).andExpect(status().isOk())
         //       .andExpect(content().string(containsString("test message")));
    }
}