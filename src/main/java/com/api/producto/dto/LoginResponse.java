package com.api.producto.dto;

import com.api.producto.entity.MaterialDef;
import java.util.List;

public class LoginResponse {
    private String mensaje;
    private String nombreCompleto;
    private String local;
    private String almacen;
    private List<MaterialDef> materiales;
    
    // Constructor
    public LoginResponse(String mensaje, String nombreCompleto, String local, String almacen, List<MaterialDef> materiales) {
        this.mensaje = mensaje;
        this.nombreCompleto = nombreCompleto;
        this.local = local;
        this.almacen = almacen;
        this.materiales = materiales;
    }
    
    // Getters y Setters
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public String getLocal() {
        return local;
    }
    
    public void setLocal(String local) {
        this.local = local;
    }
    
    public String getAlmacen() {
        return almacen;
    }
    
    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }
    
    public List<MaterialDef> getMateriales() {
        return materiales;
    }
    
    public void setMateriales(List<MaterialDef> materiales) {
        this.materiales = materiales;
    }
}
