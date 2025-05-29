package com.tcs.reto.services;

import com.tcs.reto.entities.Cliente;
import com.tcs.reto.repositories.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    public void debeCrearClienteCorrectamente() {

        Cliente cliente = new Cliente();
        cliente.setNombre("Jose Lema");
        cliente.setGenero("Masculino");
        cliente.setEdad(30);
        cliente.setIdentificacion("0101010101");
        cliente.setDireccion("Otavalo sn y principal");
        cliente.setTelefono("0982547850");
        cliente.setContrasena("Jose1234");
        cliente.setEstado(true);

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente creado = clienteService.createCliente(cliente);

        assertNotNull(creado);
        assertEquals("Jose Lema", creado.getNombre());
        assertEquals("0101010101", creado.getIdentificacion());
        assertEquals("0982547850", creado.getTelefono());
        verify(clienteRepository, times(1)).save(cliente);
    }
}
