package com.kawsay.clientservicer.controller;

import com.kawsay.clientservicer.entity.Cliente;
import com.kawsay.clientservicer.entity.ClienteDTO;
import com.kawsay.clientservicer.services.GenericoImplements;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    @Autowired
    private GenericoImplements clientService;

    @GetMapping("/testing")
    public String getTest(){
        return "Test correct";
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveClientes(@Valid @RequestBody Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }

        try {
            clientService.saveClientes(cliente);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Cliente registrado con exito");
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al registrar el cliente: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    @GetMapping("/listClientes")
    public ResponseEntity<?> getAllClientes() {
        List<ClienteDTO> clientesDTO = clientService.listClientes().stream().toList();
        if(clientesDTO.isEmpty()){
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "No hay clientes registrados");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(clientesDTO);
    }

    @PutMapping("/updateCliente")
    public ResponseEntity<?> updateClientes(@Valid @RequestBody Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }

        try{
            clientService.updateCliente(cliente);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Cliente actualizado con exito");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al actualizar el cliente: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

    }

    @DeleteMapping("/deleteCliente/{cedula}")
    public ResponseEntity<?> deleteCliente(@PathVariable("cedula") String cedula) {
        if (!cedula.matches("\\d+")) { //{5,10}
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Formato de cedula invalido");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        clientService.deleteCliente(cedula);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Cliente eliminado con exito");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getCliente/{cedula}")
    public ResponseEntity<?> getCliente(@PathVariable("cedula") String cedula){
        if (!cedula.matches("\\d+")) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Formato de cedula invalido");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        Cliente cliente = clientService.getCliente(cedula);
        if(cliente == null){
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Cliente no encontrado");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(cliente);
    }
}
