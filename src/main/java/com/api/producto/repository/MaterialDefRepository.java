package com.api.producto.repository;

import com.api.producto.entity.MaterialDef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MaterialDefRepository extends JpaRepository<MaterialDef, Integer> {
    @Query("SELECT m FROM MaterialDef m WHERE m.local = :local AND m.almacen = :almacen")
    List<MaterialDef> findByLocalAndAlmacen(String local, String almacen);
    
    //Cambios hechos apartir de aca hasta abajo, pronta explicacion:v
    @Query("SELECT DISTINCT m.local FROM MaterialDef m")
    List<String> findDistinctLocales();

    // Obtener Tipos únicos
    @Query("SELECT DISTINCT m.almacen FROM MaterialDef m")
    List<String> findDistinctAlmacenes();

}