package com.kawsay.movimientoservice.repository;

import com.kawsay.movimientoservice.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta,Long> {

    Optional<Cuenta> findByNumeroCuenta(String ncuenta);
}
