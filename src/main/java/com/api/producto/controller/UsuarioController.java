package com.api.producto.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.producto.entity.Usuario;
import com.api.producto.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> login) {
        Usuario u = usuarioService.login(login.get("username"), login.get("password"));
        if (u == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
        return ResponseEntity.ok(u);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearInventariador(@RequestBody Map<String, String> body) {
        if (!"ADMINISTRADOR".equals(body.get("rol"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo el administrador puede crear usuarios");
        }
        Usuario creado = usuarioService.crearInventariador(
            body.get("username"), body.get("password"), body.get("local"), body.get("almacen")
        );
        return ResponseEntity.ok(creado);
    }
}