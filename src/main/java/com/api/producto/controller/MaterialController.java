package com.api.producto.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.api.producto.entity.MaterialDef;
import com.api.producto.repository.MaterialDefRepository;

@RestController
@RequestMapping("/api/materiales")
@CrossOrigin(origins = "*")
public class MaterialController {

    @Autowired
    private MaterialDefRepository materialDefRepository;

    // Endpoint para obtener listas únicas de Local y Tipo
    @GetMapping("/filtros")
    public Map<String, List<String>> obtenerFiltros() {
        List<String> locales = materialDefRepository.findDistinctLocales();
        List<String> tipos = materialDefRepository.findDistinctTipos();

        Map<String, List<String>> filtros = new HashMap<>();
        filtros.put("locales", locales);
        filtros.put("tipos", tipos);
        return filtros;
    }

    // Endpoint para filtrar datos por Local y Tipo
    @PostMapping("/filtrar")
    public List<MaterialDef> filtrarMateriales(@RequestBody Map<String, String> filtros) {
        String local = filtros.get("local");
        String tipo = filtros.get("tipo");
        return materialDefRepository.findByLocalAndTipo(local, tipo);
    }
}