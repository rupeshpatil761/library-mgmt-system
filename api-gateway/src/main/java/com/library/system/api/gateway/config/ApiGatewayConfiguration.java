package com.library.system.api.gateway.config;

import com.library.system.api.gateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Autowired
    AuthenticationFilter filter;

    /**
     * Enable the filters in configuration to have authentication in placed.
     * Also, We can not restrict the other services like user, book
     * In real time those will be under private vpn and only library service can access them.
     */

    /**
     * UI app needs to send auth token in header
     */

    /**
     * Integrates with spring cloud Discovery client and provides load balancing as well.
     */
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("library-service",p -> p.path("/library/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://library-service"))
                .route("authentication-service",p -> p.path("/auth/**")
                        .uri("lb://authentication-service"))
                .build();
        // Here lb for book and user service not helpful because our every request will go through library service.

        /**
         * You can use belo filters / routes to have routing for specific service in dev env
         */

        /*.route(p -> p
                .path("/get")
                .filters(f -> f
                        .addRequestHeader("MyHeader", "MyURI")
                        .addRequestParameter("Param", "MyValue"))
                .uri("http://httpbin.org:80"))
                .route("book-service", r -> r.path("/books/**")
                        //.filters(f -> f.filter(filter))
                        .uri("lb://book-service"))
                .route("user-service", p -> p.path("/users/**")
                        //.filters(f -> f.filter(filter))
                        .uri("lb://user-service"))*/
    }
}
/*.route(p -> p.path("/currency-conversion-new/**")
        .filters(f -> f.rewritePath(
        "/currency-conversion-new/(?<segment>.*)",
        "/currency-conversion-feign/${segment}"))
        .uri("lb://currency-conversion"))*/
