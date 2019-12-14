package com.ha.helloworld.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(IndexController.class)
public class IndexControllerTest {

	@Autowired
    MockMvc mock;

    @Test
    public void index() throws Exception {
        mock.perform(get("/")).andExpect(content().string("Hello World"));
        
        MvcResult result = mock.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"))
                .andReturn();

        System.out.format("Result: %s\n", result.getResponse().getContentAsString());
    }
}
