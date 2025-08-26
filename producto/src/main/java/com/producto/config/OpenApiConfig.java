package com.producto.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Microservice Product",
                version = "1.0.0",
                description = "Endpoints Product"
        )
)
public class OpenApiConfig {
}
