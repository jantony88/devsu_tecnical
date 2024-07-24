package com.kawsay.movimientoservice.service;

import com.kawsay.movimientoservice.entity.Cuenta;
import com.kawsay.movimientoservice.entity.Movimiento;

public interface GenericoImplements {
    Movimiento saveMovimientos(Movimiento movimientos);

    Cuenta saveCuenta(Cuenta cuenta);
}
