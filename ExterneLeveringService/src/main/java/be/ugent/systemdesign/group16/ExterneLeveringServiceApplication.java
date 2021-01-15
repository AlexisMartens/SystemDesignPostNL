package be.ugent.systemdesign.group16;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import be.ugent.systemdesign.group16.API.messaging.Channels;
import be.ugent.systemdesign.group16.application.ExterneLeveringService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAsync
@EnableBinding(Channels.class)
@SpringBootApplication
public class ExterneLeveringServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(ExterneLeveringServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ExterneLeveringServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner testBestellingDataModelRepository(ExterneLeveringService service) {
		return (args) ->{
			log.info("$Testing ExterneLeveringService.");			
		};
	}

}
