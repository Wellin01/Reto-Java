package com.tcs.reto.controllers;

import com.tcs.reto.dto.ReporteMovimientoDTO;
import com.tcs.reto.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reportes")

public class ReporteController {
    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public List<ReporteMovimientoDTO> obtenerReporte(
            @RequestParam Long clienteId,
            @RequestParam LocalDate desde,
            @RequestParam LocalDate hasta) {
        return movimientoService.obtenerReporte(clienteId, desde, hasta);
    }
}
