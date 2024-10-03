package com.optifolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
public class CorsConfig {

    // Define a Bean for CorsConfigurationSource to manage CORS policy
    @Bean
    public CorsConfigurationSource corsConfigurationSource()
    {
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfigurationConfig=new CorsConfiguration();
//        corsConfigurationConfig.setAllowCredentials(allowedCredentials);
        corsConfigurationConfig.addAllowedOrigin("*");
        corsConfigurationConfig.addAllowedHeader("*");
        corsConfigurationConfig.addAllowedMethod("*");
        source.registerCorsConfiguration("/**",corsConfigurationConfig);
        return  source;
    }
}
