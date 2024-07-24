package com.kawsay.clientservicer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfiguration
public class ClientsControllerTest {
    @Autowired
    private MockMvc test;

    // Test methods for ClientController
    @Test
    public void status_ok200() throws Exception {
        test.perform(MockMvcRequestBuilders.get("/clientes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
