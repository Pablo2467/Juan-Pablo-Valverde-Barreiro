package com.inventrack.repository;

import com.inventrack.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Consulta por método: productos cuyo nombre contiene el texto, sin importar mayúsculas
    List<Product> findByNameContainingIgnoreCase(String name);
}