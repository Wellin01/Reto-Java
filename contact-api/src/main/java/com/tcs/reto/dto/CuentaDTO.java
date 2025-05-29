package com.tcs.reto.dto;

import lombok.Data;

@Data
public class CuentaDTO {
    private String numeroCuenta;
    private String tipoCuenta;
    private double saldoInicial;
    private double saldo;
    private Boolean estado;
    private ClienteDTO cliente;
}

