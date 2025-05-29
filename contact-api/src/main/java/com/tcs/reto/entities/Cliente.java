package com.tcs.reto.entities;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cliente extends Persona {
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "La contraseña no puede estar vacía y debe tener al menos 8 caracteres, incluyendo una letra y un número"
    )
    private String contrasena;

    @NotNull(message = "El estado del cliente no puede ser nulo (null), solo puede ser true o false")
    private Boolean estado;
}
