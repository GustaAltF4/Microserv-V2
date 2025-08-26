package com.producto.repository;

import com.producto.entitty.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    Product findByName (String name);
    List<Product> findByUserId(Integer userId);
}
