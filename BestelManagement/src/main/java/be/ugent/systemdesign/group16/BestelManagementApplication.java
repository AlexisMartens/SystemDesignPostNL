package be.ugent.systemdesign.group16;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import be.ugent.systemdesign.group16.infrastructure.*;
import be.ugent.systemdesign.group16.domain.*;

@SpringBootApplication
public class BestelManagementApplication {

	private static final Logger log = LoggerFactory.getLogger(BestelManagementApplication.class);
	
	
	public static void main(String[] args) {
		SpringApplication.run(BestelManagementApplication.class, args);
	}
	
	private static void logBestellingDataModels(List<BestellingDataModel> bestellingen) {
		log.info("-Number of bestellingen found: {}", bestellingen.size());
		for(BestellingDataModel bestelling : bestellingen) {
			log.info("--bestellingId {};"
					+ " Type {}, naamAfzender {}, straatAfzender {}, postcodeAfzender {},"
					+ " naamOntvanger {}, straatOntvanger {}, postcodeOntvanger {}, status {},"
					+ " spoed {}, extern {}, externeLeveringService {}."
					,
					bestelling.getBestellingId(),
					bestelling.getTypeBestelling(), bestelling.getNaamAfzender(), bestelling.getStraatAfzender(),
					bestelling.getPostcodeAfzender(), bestelling.getNaamOntvanger(), bestelling.getStraatOntvanger(), 
				bestelling.getPostcodeOntvanger(), bestelling.getStatus(), bestelling.getSpoed(),
				bestelling.getExtern(), bestelling.getExterneLeveringService());
		}
	}
	
	@Bean
	CommandLineRunner testBestellingDataModelRepository(BestellingDataModelRepository repo) {
		return (args) ->{
			log.info("$Testing BestellingDataModelJPARepository.");
			  
			log.info(">Find all Bestellingen from database."); List<BestellingDataModel>
			bestellingenAll = repo.findAll(); logBestellingDataModels(bestellingenAll);
		};
	}

}
