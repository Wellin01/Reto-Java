package com.tcs.reto.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReporteMovimientoDTO {
    private LocalDate fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private double saldoInicial;
    private boolean estado;
    private double movimiento;
    private double saldoDisponible;
}
