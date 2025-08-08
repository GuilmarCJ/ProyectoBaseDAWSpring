package com.api.producto.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.api.producto.entity.MaterialDef;
import com.api.producto.entity.Usuario;
import com.api.producto.repository.MaterialDefRepository;
import com.api.producto.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/materiales")
@CrossOrigin(origins = "*")
public class MaterialController {

    @Autowired
    private MaterialDefRepository materialDefRepository;
    @Autowired
    private UsuarioRepository usuarioRepo;

    // Endpoint para obtener listas únicas de Local y Tipo
    @GetMapping("/filtros")
    public Map<String, List<String>> obtenerFiltros() {
        List<String> locales = materialDefRepository.findDistinctLocales();
        List<String> almacenes = materialDefRepository.findDistinctAlmacenes();

        Map<String, List<String>> filtros = new HashMap<>();
        filtros.put("locales", locales);
        filtros.put("almacenes", almacenes);
        return filtros;
    }
    //Entiendelo calvo y me explicas
    // Endpoint para filtrar datos por Local y Tipo
    @PostMapping("/filtrar")
    public List<MaterialDef> filtrarMateriales(@RequestBody Map<String, String> filtros) {
        String local = filtros.get("local");
        String almacen = filtros.get("almacen");
        return materialDefRepository.findByLocalAndAlmacen(local, almacen);
    }
    
    @GetMapping("/ver")
    public List<MaterialDef> verMateriales(@RequestParam String username) {
        Usuario u = usuarioRepo.findByUsername(username).orElse(null);
        if (u == null) return List.of();

        if ("ADMINISTRADOR".equals(u.getRol())) {
            return materialDefRepository.findAll();
        } else {
            return materialDefRepository.findByLocalAndAlmacen(u.getLocal(), u.getAlmacen());
        }
    }
}