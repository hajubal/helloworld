package com.ha.helloworld.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class IndexControllerTest {

    @Test
    public void index(@Autowired MockMvc mock) throws Exception {
//        mock.perform(get("/")).andExpect(content().string("Hello World"));
        
        MvcResult result = mock.perform(get("/"))
        		.andExpect(status().is3xxRedirection())
//                .andExpect(status().isOk())
//                .andExpect(content().string("Hello World"))
                .andReturn();

        System.out.format("Result: %s\n", result.getResponse().getContentAsString());
    }
}
