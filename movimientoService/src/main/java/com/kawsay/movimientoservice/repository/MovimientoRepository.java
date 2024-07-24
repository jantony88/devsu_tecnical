package com.kawsay.movimientoservice.repository;

import com.kawsay.movimientoservice.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento,Long> {

    Optional<Movimiento> findById(Long aLong);
}
