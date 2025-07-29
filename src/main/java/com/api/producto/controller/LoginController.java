package com.api.producto.controller;

import java.util.Optional;
//aaaa
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.producto.entity.Usuario;
import com.api.producto.repository.UsuarioRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") 
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public String login(@RequestBody Usuario loginData) {
        Optional<Usuario> usuario = usuarioRepository.findByUsernameAndPassword(
                loginData.getUsername(), loginData.getPassword()
        );

        if (usuario.isPresent()) {
            return "Login correcto - Bienvenido " + usuario.get().getNombre_completo();
        } else {
            return "Usuario o contraseña incorrectos";
        }
    }
}
