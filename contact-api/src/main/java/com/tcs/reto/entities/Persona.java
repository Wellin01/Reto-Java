package com.tcs.reto.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;


@MappedSuperclass
@Data
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(regexp = "^[^\\d]*$", message = "El nombre no debe contener números")
    private String nombre;

    @Pattern(
            regexp = "^(Masculino|Femenino|No binario)$",
            message = "El género debe ser Masculino, Femenino o No binario"
    )
    private String genero;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "La edad mínima es 18")
    private Integer edad;

    @NotBlank(message = "La identificación es obligatoria")
    @Pattern(regexp = "^\\d{10}$", message = "La identificación debe tener 10 dígitos numéricos")
    private String identificacion;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^\\d{10}$", message = "El teléfono debe tener 10 dígitos numéricos")
    private String telefono;
}
