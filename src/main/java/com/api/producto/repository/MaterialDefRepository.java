package com.api.producto.repository;

import com.api.producto.entity.MaterialDef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MaterialDefRepository extends JpaRepository<MaterialDef, Integer> {
    @Query("SELECT m FROM MaterialDef m WHERE m.local = :local AND m.almacen = :almacen")
    List<MaterialDef> findByLocalAndAlmacen(String local, String almacen);
}