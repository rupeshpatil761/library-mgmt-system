package com.library.system.api.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    /**
     * Integrates with spring cloud Discovery client and provides load balancing as well.
     */
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/get")
                        .filters(f -> f
                                .addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p.path("/books/**")
                        .uri("lb://book-service"))
                .route(p -> p.path("/users/**")
                        .uri("lb://user-service"))
                .route(p -> p.path("/library/**")
                        .uri("lb://library-service"))
                .build();
        // Here lb for book and user service not helpful because our every request will go through library service.
    }
}

/*.route(p -> p.path("/currency-conversion-new/**")
        .filters(f -> f.rewritePath(
        "/currency-conversion-new/(?<segment>.*)",
        "/currency-conversion-feign/${segment}"))
        .uri("lb://currency-conversion"))*/
