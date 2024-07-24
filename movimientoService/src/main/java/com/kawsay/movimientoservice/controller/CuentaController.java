package com.kawsay.movimientoservice.controller;

import com.kawsay.movimientoservice.entity.Cuenta;
import com.kawsay.movimientoservice.service.GenericoImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    private GenericoImplements cuentaService;


    @RequestMapping("/test")
    public String crearMovimiento() {
        return "Movimiento testing";
    }

    @PostMapping("/register-cuenta")
    public ResponseEntity<?> saveCuenta(@RequestBody Cuenta cuenta, BindingResult result){
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
            cuentaService.saveCuenta(cuenta);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Cuenta registrada con éxito");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al registrar la cuenta: "+e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
