package be.ugent.systemdesign.group16.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
@EnableAutoConfiguration
public class DocumentationController implements SwaggerResourcesProvider {

	
	@Override
	   public List<SwaggerResource> get() {
	      List<SwaggerResource> resources = new ArrayList<>();
	      resources.add(swaggerResource("bestel-service:2000", "/api/bestel/v2/api-docs", "2.0"));
	      resources.add(swaggerResource("externelevering-service:2001", "/api/externelevering/v2/api-docs", "2.0"));
	      resources.add(swaggerResource("fullfilmentbestelling-service:2002", "/api/fulfilmentbestel/v2/api-docs", "2.0"));
	      resources.add(swaggerResource("klanten-service:2003", "/api/fulfilmentklant/v2/api-docs", "2.0"));
	      resources.add(swaggerResource("koerier-service:2004", "/api/koerier/v2/api-docs", "2.0"));
	      resources.add(swaggerResource("magazijn-service:2005", "/api/pakket/v2/api-docs", "2.0"));
	      resources.add(swaggerResource("sorteeritem-service:2006", "/api/sorteeritem/v2/api-docs", "2.0"));
	      resources.add(swaggerResource("trackandtrace-service:2008", "/api/trackandtrace/v2/api-docs", "2.0"));
	      resources.add(swaggerResource("zending-service:2010", "/api/zending/v2/api-docs", "2.0"));	      
	      return resources;
	   }
	
	private SwaggerResource swaggerResource(String name, String location, String version) {
	      SwaggerResource swaggerResource = new SwaggerResource();
	      swaggerResource.setName(name);
	      swaggerResource.setLocation(location);
	      swaggerResource.setSwaggerVersion(version);
	      return swaggerResource;
	   }
}
