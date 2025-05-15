package com.tcs.reto.repositories;

import com.tcs.reto.entities.Cuenta;
import com.tcs.reto.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {
    List<Cuenta> findByCliente(Cliente cliente);
}