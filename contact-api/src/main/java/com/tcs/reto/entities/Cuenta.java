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
    @Pattern(regexp = "\\d{7}", message = "El número de cuenta debe tener exactamente 7 dígitos numéricos")
    private String numeroCuenta;

    @NotBlank(message = "El tipo de cuenta es obligatorio")
    @Pattern(
            regexp = "^(Ahorros|Corriente)$",
            message = "El tipo de cuenta debe ser 'Ahorros' o 'Corriente'"
    )
    private String tipoCuenta;

    @Min(value = 0, message = "El saldo inicial no puede ser negativo")
    private double saldoInicial;
    private double saldo;

    @NotNull(message = "El estado de la cuenta no puede ser nulo (null), solo puede ser true o false")
    private Boolean estado;

    @NotNull(message = "Debe especificar el ID cliente")

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
