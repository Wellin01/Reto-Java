package com.tcs.reto.controllers;

import com.tcs.reto.entities.Movimiento;
import com.tcs.reto.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/movimientos")

public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public Movimiento registrarMovimiento(@RequestBody @Valid Movimiento movimiento) {
        return movimientoService.registrarMovimiento(movimiento);
    }

    @GetMapping
    public List<Movimiento> listarMovimientos() {
        return movimientoService.listarTodos();
    }
}
