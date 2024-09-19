package com.riwi.QuickNote.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @SuppressWarnings("unused")
            public void addCorsMappins(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST","PUT", "DELETE");
            };
        };
    };
};