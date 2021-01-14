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
				.route(r -> r.host("*").and().path("/api/bestel/**").uri("http://localhost:2000"))
				.route(r -> r.host("*").and().path("/api/externelevering/**").uri("http://localhost:2001"))
				.route(r -> r.host("*").and().path("/api/fulfilmentbestel/**").uri("http://localhost:2002"))
				.route(r -> r.host("*").and().path("/api/fulfilmentklant/**").uri("http://localhost:2003"))
				.route(r -> r.host("*").and().path("/api/koerier/**").uri("http://localhost:2004"))
				.route(r -> r.host("*").and().path("/api/magazijnservice/**").uri("http://localhost:2005"))
				.route(r -> r.host("*").and().path("/api/sorteeritem/brief").uri("http://localhost:2006"))
				.route(r -> r.host("*").and().path("/api/trackandtrace/**").uri("http://localhost:2008"))
				.route(r -> r.host("*").and().path("/api/zendingen/**").uri("http://localhost:2010"))
				.build();
	}
}
