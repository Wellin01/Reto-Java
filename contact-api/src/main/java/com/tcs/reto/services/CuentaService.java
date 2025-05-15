package com.tcs.reto.services;

import com.tcs.reto.entities.Cuenta;
import com.tcs.reto.repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tcs.reto.exceptions.ResourceAlreadyExistsException;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

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
        cuenta.setSaldo(cuenta.getSaldoInicial());
        return cuentaRepository.save(cuenta);
    }

    public Cuenta update(String numero, Cuenta nueva) {
        Cuenta cuenta = cuentaRepository.findById(numero).orElse(null);
        if (cuenta != null) {
            cuenta.setTipoCuenta(nueva.getTipoCuenta());
            cuenta.setSaldoInicial(nueva.getSaldoInicial());
            cuenta.setEstado(nueva.getEstado());
            cuenta.setCliente(nueva.getCliente());
            return cuentaRepository.save(cuenta);
        }
        return null;
    }

    public void delete(String numero) {
        cuentaRepository.deleteById(numero);
    }
}
