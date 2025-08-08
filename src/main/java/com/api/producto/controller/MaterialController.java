package com.api.producto.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    private UsuarioRepository usuarioRepository;

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
        Usuario u = usuarioRepository.findByUsername(username).orElse(null);
        if (u == null) return List.of();

        if ("ADMINISTRADOR".equals(u.getRol())) {
            return materialDefRepository.findAll();
        } else {
            return materialDefRepository.findByLocalAndAlmacen(u.getLocal(), u.getAlmacen());
        }
    }
    
    @GetMapping("/materiales")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public List<MaterialDef> verTodosLosMateriales() {
        return materialDefRepository.findAll();
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> editarConteoReconteo(@PathVariable int id, @RequestBody MaterialDef datos) {
        MaterialDef material = materialDefRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Material no encontrado"));

        material.setConteo(datos.getConteo());
        material.setReconteo(datos.getReconteo());
        materialDefRepository.save(material);

        return ResponseEntity.ok("Conteo y reconteo actualizados");
    }

    @GetMapping("/conteos")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public List<Map<String, Object>> verConteosYUsuarios() {
        return materialDefRepository.findAll().stream()
            .map(m -> {
                Map<String, Object> datos = new HashMap<>();
                datos.put("material", m.getMaterial());
                datos.put("conteo", m.getConteo());
                datos.put("reconteo", m.getReconteo());
                datos.put("usuario", m.getUsuario());
                return datos;
            })
            .toList();
    }

    @GetMapping("/mios")
    @PreAuthorize("hasRole('INVENTARIADOR')")
    public List<MaterialDef> verMisMateriales(Authentication auth) {
        Usuario usuario = usuarioRepository.findByUsername(auth.getName())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return materialDefRepository.findByLocalAndAlmacenAndUsuario(
            usuario.getLocal(),
            usuario.getAlmacen(),
            usuario.getUsername()
        );
    }
    
    
}