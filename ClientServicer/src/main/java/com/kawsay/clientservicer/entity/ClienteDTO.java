package com.kawsay.clientservicer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
