package be.ugent.systemdesign.group16;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import be.ugent.systemdesign.group16.API.messaging.Channels;
import be.ugent.systemdesign.group16.application.FulfilmentBestelService;
import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Bestelling;
import be.ugent.systemdesign.group16.domain.BestellingStatus;
@EnableAsync
@EnableBinding(Channels.class)
@SpringBootApplication
public class FulfilmentBestelManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FulfilmentBestelManagementApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(FulfilmentBestelManagementApplication.class);
	
	@Value("${spring.cloud.stream.bindings." + Channels.GET_KLANTEN_DATA_RESPONSE + ".destination}")
	String responseDestination;
	
	private static void logResponse(Response response) {
		log.info("-response status[{}] message[{}]", response.status, response.message);
	}

	@Bean
	CommandLineRunner testFulfilmentBestelService(FulfilmentBestelService service) {
		return (args) -> {
			log.info("$Testing BestelService.");

			log.info(">maak een bestelling");
			Bestelling newbestelling = new Bestelling("Pakket","Jan Vander Broek", "7000", "kaastraat 150", "Gent", "Belgie", 
					"Hans Landeghem", "4564", "geefstraat 4", "Geverghem", "Belgie", false);
			
			log.info(">plaatsBestelling");
			Response response = service.plaatsBestelling(newbestelling);
			log.info(">logResponse");
			logResponse(response);
		};
	}
}
