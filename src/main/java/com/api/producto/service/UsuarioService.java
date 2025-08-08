package com.api.producto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.producto.entity.Usuario;
import com.api.producto.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario login(String username, String password) {
        return usuarioRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
    }

    public Usuario crearInventariador(String username, String password, String local, String almacen) {
        Usuario u = new Usuario();
        u.setUsername(username);
        u.setPassword(password);
        u.setRol("INVENTARIADOR");
        u.setLocal(local);
        u.setAlmacen(almacen);
        return usuarioRepository.save(u);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}