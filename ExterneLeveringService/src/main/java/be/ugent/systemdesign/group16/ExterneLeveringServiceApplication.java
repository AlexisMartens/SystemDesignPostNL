package be.ugent.systemdesign.group16;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
import be.ugent.systemdesign.group16.domain.BestellingStatus;
import be.ugent.systemdesign.group16.infrastructure.BestellingDataModel;
import be.ugent.systemdesign.group16.infrastructure.BestellingDataModelRepository;

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
			
			//testen
			
		};
	}

}
