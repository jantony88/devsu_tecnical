package com.kawsay.clientservicer.services;

import com.kawsay.clientservicer.entity.Cliente;
import com.kawsay.clientservicer.entity.ClienteDTO;
import com.kawsay.clientservicer.entity.Persona;

import java.util.List;

public interface GenericoImplements {

    Cliente saveClientes(Cliente cliente) throws Exception;

    List<ClienteDTO> listClientes();

    Cliente updateCliente(Cliente response);

    void deleteCliente(String cedula);

    Cliente getCliente(String cedula);
}
