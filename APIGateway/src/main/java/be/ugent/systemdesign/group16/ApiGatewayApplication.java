package be.ugent.systemdesign.group16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.host("*").and().path("/api/bestel/**").uri("http://bestel-service:2000"))
				.route(r -> r.host("*").and().path("/api/externelevering/**").uri("http://externelevering-service:2001"))
				.route(r -> r.host("*").and().path("/api/fulfilmentbestel/**").uri("http://fullfilmentbestelling-service:2002"))
				.route(r -> r.host("*").and().path("/api/fulfilmentklant/**").uri("http://klanten-service:2003"))
				.route(r -> r.host("*").and().path("/api/koerier/**").uri("http://koerier-service:2004"))
				.route(r -> r.host("*").and().path("/api/magazijnservice/**").uri("http://magazijn-service:2005"))
				.route(r -> r.host("*").and().path("/api/sorteeritem/brief").uri("http://sorteeritem-service:2006"))
				.route(r -> r.host("*").and().path("/api/trackandtrace/**").uri("http://trackandtrace-service:2008"))
				.route(r -> r.host("*").and().path("/api/zendingen/**").uri("http://zending-service:2010"))
				.build();
	}
}
