package com.microservice.APIRegistryService.APIRegistry.configurations;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : Samyam Kafle
 * @Portfolio : https://samyamkafle.com.np
 * @Project : microserviceDemo
 * @CreatedDate : 20/12/2024, Friday
 **/
@Configuration
public class RoutesConfiguration {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(route -> route.path("/**").uri("lb://TODOSERVICE"))
                .build();
    }
}
