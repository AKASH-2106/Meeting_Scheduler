package com.project.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		String event_endpoint = "http://192.168.134.61:8080/";
		return builder.routes()
				.route(p -> p
						.path("/get")
						.uri("http://httpbin.org:80"))
				.route(p -> p
						.path("/auth-service/**")
						.filters(f -> f.rewritePath("/auth-service/(?<RID>.*)", "/${RID}"))
						.uri("http://localhost:2000/"))
				.route(p -> p
						.path("/user-service/**")
						.filters(f -> f.rewritePath("/user-service/(?<RID>.*)", "/${RID}"))
						.uri("http://localhost:2001/"))
				.route(p -> p
						.path("/scheduler-service/**")
						.filters(f -> f.rewritePath("/scheduler-service/(?<RID>.*)", "/${RID}"))
						.uri("http://localhost:2003/"))
				.route(p -> p
						.path("/event-service/**")
						.filters(f -> f.rewritePath("/event-service/(?<RID>.*)", "/${RID}"))
						.uri(event_endpoint))
				.build();
	}

}
