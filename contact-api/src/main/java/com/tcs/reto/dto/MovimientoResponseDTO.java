package com.tcs.reto.dto;
import java.time.LocalDate;

public class MovimientoResponseDTO {
    private Long id;
    private LocalDate fecha;
    private String tipoMovimiento;
    private double valor;
    private double saldo;
    private Long numeroCuenta;
}
