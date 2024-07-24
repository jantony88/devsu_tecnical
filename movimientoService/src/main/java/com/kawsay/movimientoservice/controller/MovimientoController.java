package com.kawsay.movimientoservice.controller;

import com.kawsay.movimientoservice.entity.Movimiento;
import com.kawsay.movimientoservice.service.GenericoImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Binding;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private GenericoImplements movimientosService;

    @PostMapping("/register-movimientos")
    public ResponseEntity<?> registerMovimientos(@RequestBody Movimiento movimientos, BindingResult result){
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("errors", errors);
            return ResponseEntity.badRequest().body(response);
        }
        movimientosService.saveMovimientos(movimientos);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Movimiento registrado con éxito");
        return ResponseEntity.ok().body("Movimiento registrado con éxito");
    }
}
