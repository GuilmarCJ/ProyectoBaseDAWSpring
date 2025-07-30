package com.api.producto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "materiales_def")
public class MaterialDef {
    @Id
    private Integer id;
    private String material;
    private String descripcion;
    private String almacen;
    private String lote;
    private Double cantidad;
    private Double conteo;
    private Double reconteo;
    private String local;
    private String umb;
    private String parihuela;
    private String ubicacion;
    private String cta;
    private String usuario;
    private String estado;
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getMaterial() {
        return material;
    }
    
    public void setMaterial(String material) {
        this.material = material;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getAlmacen() {
        return almacen;
    }
    
    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }
    
    public String getLote() {
        return lote;
    }
    
    public void setLote(String lote) {
        this.lote = lote;
    }
    
    public Double getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
    
    public Double getConteo() {
        return conteo;
    }
    
    public void setConteo(Double conteo) {
        this.conteo = conteo;
    }
    
    public Double getReconteo() {
        return reconteo;
    }
    
    public void setReconteo(Double reconteo) {
        this.reconteo = reconteo;
    }
    
    public String getLocal() {
        return local;
    }
    
    public void setLocal(String local) {
        this.local = local;
    }
    
    public String getUmb() {
        return umb;
    }
    
    public void setUmb(String umb) {
        this.umb = umb;
    }
    
    public String getParihuela() {
        return parihuela;
    }
    
    public void setParihuela(String parihuela) {
        this.parihuela = parihuela;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }
    
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public String getCta() {
        return cta;
    }
    
    public void setCta(String cta) {
        this.cta = cta;
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
}