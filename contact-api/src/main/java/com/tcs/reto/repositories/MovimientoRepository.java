package com.tcs.reto.repositories;

import com.tcs.reto.entities.Movimiento;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaNumeroCuentaAndFechaBetween(@NotBlank(message = "El número de cuenta es obligatorio") String numeroCuenta, LocalDate desde, LocalDate hasta);
}

