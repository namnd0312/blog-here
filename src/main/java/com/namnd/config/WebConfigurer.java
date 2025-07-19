package com.namnd.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@Configuration
public class WebConfigurer {

    private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    @Bean
    public CorsFilter corsFilter() {
        log.debug("Registering CORS filter");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedOrigins(Collections.singletonList("*"));

        source.registerCorsConfiguration("/api/**", config);

        return new CorsFilter(source);
    }
}
