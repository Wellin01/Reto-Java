package com.tcs.reto.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.reto.entities.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

public class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeCrearClienteExitosamente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNombre("Test User");
        cliente.setGenero("Masculino");
        cliente.setEdad(28);
        cliente.setIdentificacion("9999999999");
        cliente.setDireccion("Calle Falsa 123");
        cliente.setTelefono("0909090909");
        cliente.setContrasena("clave123");
        cliente.setEstado(true);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Test User"))
                .andExpect(jsonPath("$.identificacion").value("9999999999"));
    }

}
