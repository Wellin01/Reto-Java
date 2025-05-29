package com.tcs.reto.services;

import com.tcs.reto.dto.ReporteMovimientoDTO;
import com.tcs.reto.entities.Cliente;
import com.tcs.reto.entities.Cuenta;
import com.tcs.reto.entities.Movimiento;
import com.tcs.reto.exceptions.ResourceNotFoundException;
import com.tcs.reto.repositories.ClienteRepository;
import com.tcs.reto.repositories.CuentaRepository;
import com.tcs.reto.repositories.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service

public class MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Movimiento> listarTodos() {
        return movimientoRepository.findAll();
    }

    public Movimiento registrarMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getNumeroCuenta())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró la cuenta con número: " + movimiento.getCuenta().getNumeroCuenta()
                ));

        if (!Boolean.TRUE.equals(cuenta.getEstado())) {
            throw new IllegalStateException("La cuenta está inactiva y no permite movimientos.");
        }

        double saldoActual = cuenta.getSaldo();
        double monto = movimiento.getValor();

        if (movimiento.getTipoMovimiento() == null) {
            throw new IllegalArgumentException("El tipo de movimiento es obligatorio.");
        }

        if (movimiento.getTipoMovimiento().equalsIgnoreCase("Retiro")) {
            monto = -Math.abs(monto);
        } else if (movimiento.getTipoMovimiento().equalsIgnoreCase("Depósito")) {
            monto = Math.abs(monto);
        } else {
            throw new IllegalArgumentException("Tipo de movimiento inválido. Usa Retiro o Depósito.");
        }

        double nuevoSaldo = saldoActual + monto;

        if (nuevoSaldo < 0) {
            throw new IllegalStateException("Saldo no disponible para realizar el retiro.");
        }

        cuenta.setSaldo(nuevoSaldo);
        cuentaRepository.save(cuenta);

        movimiento.setCuenta(cuenta);
        movimiento.setValor(monto);
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setFecha(LocalDate.now());

        return movimientoRepository.save(movimiento);
    }

    public List<ReporteMovimientoDTO> obtenerReporte(Long clienteId, LocalDate desde, LocalDate hasta) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + clienteId));

        List<Cuenta> cuentas = cuentaRepository.findByCliente(cliente);
        List<ReporteMovimientoDTO> reporte = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {
            List<Movimiento> movimientos = movimientoRepository
                    .findByCuentaNumeroCuentaAndFechaBetween(cuenta.getNumeroCuenta(), desde, hasta);

            for (Movimiento mov : movimientos) {
                reporte.add(new ReporteMovimientoDTO(
                        mov.getFecha(),
                        cliente.getNombre(),
                        cuenta.getNumeroCuenta(),
                        cuenta.getTipoCuenta(),
                        cuenta.getSaldoInicial(),
                        cuenta.getEstado(),
                        mov.getValor(),
                        mov.getSaldo()
                ));
            }
        }
        return reporte;
    }
}
