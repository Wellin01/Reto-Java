package com.tcs.reto.services;

import com.tcs.reto.entities.Cliente;
import com.tcs.reto.entities.Cuenta;
import com.tcs.reto.repositories.ClienteRepository;
import com.tcs.reto.repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tcs.reto.exceptions.ResourceAlreadyExistsException;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cuenta> getAll() {
        return cuentaRepository.findAll();
    }

    public Cuenta getByNumero(String numero) {
        return cuentaRepository.findById(numero).orElse(null);
    }

    public Cuenta create(Cuenta cuenta) {
        boolean existe = cuentaRepository.existsById(cuenta.getNumeroCuenta());
        if (existe) {
            throw new ResourceAlreadyExistsException("Ya existe una cuenta con número: " + cuenta.getNumeroCuenta());
        }

        if (cuenta.getCliente() == null || cuenta.getCliente().getId() == null) {
            throw new IllegalArgumentException("Debe proporcionar el ID del cliente");
        }

        boolean clienteExiste = clienteRepository.existsById(cuenta.getCliente().getId());
        if (!clienteExiste) {
            throw new IllegalArgumentException("No existe un cliente con ID: " + cuenta.getCliente().getId());
        }

        cuenta.setSaldo(cuenta.getSaldoInicial());
        return cuentaRepository.save(cuenta);
    }

    public Cuenta update(String numero, Cuenta nueva) {
        Cuenta cuenta = cuentaRepository.findById(numero).orElse(null);
        if (cuenta != null) {
            cuenta.setTipoCuenta(nueva.getTipoCuenta());
            cuenta.setSaldoInicial(nueva.getSaldoInicial());
            cuenta.setEstado(nueva.getEstado());
            cuenta.setSaldo(nueva.getSaldoInicial());

            if (nueva.getCliente() != null && nueva.getCliente().getId() != null) {
                Cliente clienteExistente = clienteRepository.findById(nueva.getCliente().getId()).orElse(null);
                if (clienteExistente != null) {
                    cuenta.setCliente(clienteExistente);
                } else {
                    throw new IllegalArgumentException("Cliente no existe con id: " + nueva.getCliente().getId());
                }
            } else {
                cuenta.setCliente(null);
            }

            return cuentaRepository.save(cuenta);
        }
        return null;
    }

    public void delete(String numero) {
        cuentaRepository.deleteById(numero);
    }
}
