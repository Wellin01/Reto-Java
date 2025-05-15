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
    private String contrasena;

    @NotNull(message = "El estado no puede ser nulo")
    private Boolean estado;
}
