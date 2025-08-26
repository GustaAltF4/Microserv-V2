package com.user.clients;

import com.user.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-microserv", url = "${PRODUCT_MICROSERV_URL:}")
public interface ProductoFeignClient {

    @GetMapping("/product/user_id/{user_id}")
    public List<ProductDto> obtenerProductos(@PathVariable("user_id") Integer user_id);
}
