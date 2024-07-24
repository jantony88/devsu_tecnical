package com.kawsay.movimientoservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private String tipoMovimiento;
    @Positive
    private double valor;
    private double saldo;
    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;
}
