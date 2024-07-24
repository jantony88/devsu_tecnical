package com.kawsay.clientservicer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientsControllerTest {
    @Autowired
    private MockMvc test;

    // Test methods for ClientController
    @Test
    public void status_ok200() throws Exception {
        test.perform(MockMvcRequestBuilders.get("/clientes/listClientes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    @Test
    public void status_post() throws  Exception{
        MvcResult result = test.perform(MockMvcRequestBuilders.post("/clientes/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"identificacion\":\"123456781\",\"nombre\":\"Juan\",\"apellido\":\"Perez\",\"direccion\":\"Calle 123, 456, Ciudad \", \"genero\": \"m\",\"telefono\": \"12335\",\"contrasena\": \"123\",\"estado\": true}"))
                .andExpect(status().isCreated()).andReturn();;

        if (result.getResponse().getStatus() != 201) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Respuesta recibida: " + result.getResponse().getContentAsString());
        }
    }

    @Test
    public void status_post_clienteExistente() throws Exception {
        String jsonCliente = "{\"identificacion\":\"123456789\",\"nombre\":\"Juan\",\"apellido\":\"Perez\",\"direccion\":\"Calle 123, 456, Ciudad X\",\"telefono\": \"12335\",\"contrasena\": \"123\",\"estado\": true}";

        test.perform(MockMvcRequestBuilders.post("/clientes/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCliente))
                .andExpect(status().isConflict())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Error al registrar el cliente: Cliente con número de cédula '123456780' ya existe."));
    }
}
