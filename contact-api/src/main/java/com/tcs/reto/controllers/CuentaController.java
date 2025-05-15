package com.tcs.reto.controllers;

import com.tcs.reto.entities.Cuenta;
import com.tcs.reto.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<Cuenta> getAll() {
        return cuentaService.getAll();
    }

    @GetMapping("/{numero}")
    public Cuenta getByNumero(@PathVariable String numero) {
        return cuentaService.getByNumero(numero);
    }

    @PostMapping
    public Cuenta create(@RequestBody @Valid Cuenta cuenta) {
        return cuentaService.create(cuenta);
    }

    @PutMapping("/{numero}")
    public Cuenta update(@PathVariable String numero, @RequestBody @Valid Cuenta cuenta) {
        return cuentaService.update(numero, cuenta);
    }

    @DeleteMapping("/{numero}")
    public void delete(@PathVariable String numero) {
        cuentaService.delete(numero);
    }
}
