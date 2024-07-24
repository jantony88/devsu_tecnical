package com.kawsay.movimientoservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cuenta_id;
    @Column(unique = true)
    @NotBlank(message = "Numero de cuenta requerido")
    private String numeroCuenta;
    @NotBlank(message = "Tipo de cuenta requerido")
    private String tipoCuenta;
    private double saldoInicial;
    private boolean estado;
    private Long clienteId;
    @OneToMany(mappedBy = "cuenta")
    private Set<Movimiento> movimientos = new HashSet<>();
}
