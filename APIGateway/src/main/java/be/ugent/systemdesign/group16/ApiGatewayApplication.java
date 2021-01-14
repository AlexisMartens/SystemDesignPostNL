package be.ugent.systemdesign.group16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				// Inpatient service routes
				.route(r -> r.host("*").and().path("api/bestel/**").uri("http://localhost:2222"))
				// Reception service routes
				.route(r -> r.host("*").and().path("api/fulfilmentbestel/**").uri("http://localhost:2223"))
				.route(r -> r.host("*").and().path("api/fulfilmentklant/**").uri("http://localhost:2223"))
				.route(r -> r.host("*").and().path("api/koerier/**").uri("http://localhost:2223"))
				.route(r -> r.host("*").and().path("api/MagazijnService/**").uri("http://localhost:2223"))
				.route(r -> r.host("*").and().path("api/sorteeritem/**").uri("http://localhost:2223"))
				.route(r -> r.host("*").and().path("api/koerier/**").uri("http://localhost:2223"))

				// it is also possible to give independent paths:
				// .route(r ->
				// r.host("*").and().path("/inpatients").uri("http://localhost:2222/inpatients") )

				.build();
	}
}
