package com.kawsay.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/clientes/**")
						.uri("lb://cliente_service"))
				.route(r -> r.path("/cuenta/**")
						.uri("lb://cliente_service"))
				.route(r -> r.path("/movimientos/**")
						.uri("lb://movimientos_service"))
				.build();
	}
}
