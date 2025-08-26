package com.producto.controller;

import com.producto.entitty.Product;
import com.producto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    public ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = service.ListAllProd();
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Product> getProdById(@PathVariable Integer id){
        Product prod = service.getProdById(id);
        if (prod == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(prod);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Product> getProdByName(@PathVariable String name){
        Product prod = service.getProdByName(name);
        if (prod == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(prod);
    }

    @GetMapping("/user_id/{user_id}")
    public ResponseEntity<List<Product>> getProdOfUser(@PathVariable Integer user_id){
        List<Product> products = service.getProdByUserId(user_id);
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(products);
    }

    //POST________________________________________________________

    @PostMapping("/add")
    public ResponseEntity<Product> addProd (@RequestBody Product product){
        Product created = service.addProd(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    //DELETE______________________________________________________

    @DeleteMapping("/del/id/{id}")
    public ResponseEntity<Void> deleteProdById (@PathVariable Integer id){
        service.deleteProdById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/del/name/{name}")
    public ResponseEntity<Void> deleteProdByName(@PathVariable String name){
        service.deleteProdByName(name);
        return ResponseEntity.noContent().build();
    }

    //PUT__________________________________________________________

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProd(@PathVariable Integer id, @RequestBody Product product){
        Product updated = service.updateProd(id, product);
        return ResponseEntity.ok(updated);
    }

}
