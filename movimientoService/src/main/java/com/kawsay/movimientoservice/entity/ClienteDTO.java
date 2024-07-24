package com.kawsay.movimientoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private String identificacion;
    private String nombres;
    private String direccion;
    private String telefono;
    private String contrasena;
    private boolean estado;
}
