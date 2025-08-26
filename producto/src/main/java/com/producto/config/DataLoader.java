package com.producto.config;

import com.producto.entitty.Product;
import com.producto.repository.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepo prodRepo;


    public DataLoader(ProductRepo prodRepo) {
        this.prodRepo = prodRepo;
    }

    @Override
    public void run(String... args) {
        if (prodRepo.count() == 0) {
            prodRepo.saveAll(
                    List.of(
                            new Product("producto1", "aqui va la descricion del producto 1", 2000.00, 1, "https://placehold.co/400x300/000000/FFFFFF?text=Producto1"), // negro con texto blanco
                            new Product("producto2", "aqui va la descricion del producto 2", 3500.00, 2, "https://placehold.co/400x300/FF0000/FFFFFF?text=Producto2"), // rojo con texto blanco
                            new Product("producto3", "aqui va la descricion del producto 3", 4800.50, 1, "https://placehold.co/400x300/008000/FFFFFF?text=Producto3"), // verde con texto blanco
                            new Product("producto4", "aqui va la descricion del producto 4", 1500.00, 3, "https://placehold.co/400x300/0000FF/FFFFFF?text=Producto4"), // azul con texto blanco
                            new Product("producto5", "aqui va la descricion del producto 5", 2700.00, 3, "https://placehold.co/400x300/FFA500/000000?text=Producto5")  // naranja con texto negro


                    )
            );
        }
    }
}
