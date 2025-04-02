package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Permite todas las rutas
                        .allowedOrigins("bus-site-example.netlify.app","http://localhost:5173") // Reemplaza con la URL de tu Netlify
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                        .allowedHeaders("*") // Permite todos los headers
                        .allowCredentials(true);
            }
        };
    }
}
