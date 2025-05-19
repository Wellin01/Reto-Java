package com.tcs.reto.entities;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cuenta {

    @Id
    @NotBlank(message = "El número de cuenta es obligatorio")
    private String numeroCuenta;

    @NotBlank(message = "El tipo de cuenta es obligatorio")
    private String tipoCuenta;

    @Min(value = 0, message = "El saldo inicial no puede ser negativo")
    private double saldoInicial;
    private double saldo;

    @NotNull(message = "El estado de la cuenta es obligatorio")
    private Boolean estado;

    @NotNull(message = "Debe especificar un cliente")

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
