package com.producto.service;

import com.producto.entitty.Product;
import com.producto.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    public ProductRepo repoProd;

    public List<Product> ListAllProd(){
        return  repoProd.findAll();
    }

    public Product getProdById(Integer id){
        return repoProd.findById(id).orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }

    public List<Product> getProdByUserId (Integer user_id){
        List<Product> productos = repoProd.findByUserId(user_id);
        if (productos == null || productos.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        return productos;
    }

    public Product getProdByName(String name){
        Product producto = repoProd.findByName(name);
        if (producto == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        return  producto;
    }

    //POST_________________________________

    public Product addProd(Product prod){
        return repoProd.save(prod);
    }

    //DELETE_______________________________

    public void deleteProdById(Integer id){
        if (repoProd.existsById(id)){
            repoProd.deleteById(id);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
    }

    public void deleteProdByName(String name){
        Product product = repoProd.findByName(name);

        if (product != null){
            repoProd.delete(product);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
    }

    //PUT__________________________________

    public Product updateProd(Integer id , Product newProd){

        Product existingProd = repoProd.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

        existingProd.setName(newProd.getName());
        existingProd.setDescription(newProd.getDescription());
        existingProd.setPrice(newProd.getPrice());
        existingProd.setUserId(newProd.getUserId());
        existingProd.setImageUrl(newProd.getImageUrl());

        return repoProd.save(existingProd);

    }
}
