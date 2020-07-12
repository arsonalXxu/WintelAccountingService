package org.arsonal.accounting.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class HelloControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    void sayHello() throws Exception {
        // Arrange & Act & Assert
        mockMvc.perform(get("/v1/greeting/{name}", "hardcore"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, hardcore"));
    }

    @Test
    void sayHelloV2() throws Exception {
        // Arrange & Act & Assert
        mockMvc.perform(get("/v2/greeting").param("name", "hardcore"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"Hello, hardcore\"}"));
    }
}