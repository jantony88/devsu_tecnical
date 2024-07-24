package com.kawsay.movimientoservice.service;

import com.kawsay.movimientoservice.entity.Cuenta;
import com.kawsay.movimientoservice.entity.Movimiento;
import com.kawsay.movimientoservice.repository.CuentaRepository;
import com.kawsay.movimientoservice.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenericService implements GenericoImplements{

    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Override
    public Movimiento saveMovimientos(Movimiento movimientos) {
        return movimientoRepository.save(movimientos);
    }

    @Override
    public Cuenta saveCuenta(Cuenta cuenta) {
        Optional<Cuenta> existingcuenta = cuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta());
        if (existingcuenta.isPresent()) {
            throw new RuntimeException("La cuenta '"+ cuenta.getNumeroCuenta()+"' ya existe.");
        }
        return cuentaRepository.save(cuenta);
    }
}
