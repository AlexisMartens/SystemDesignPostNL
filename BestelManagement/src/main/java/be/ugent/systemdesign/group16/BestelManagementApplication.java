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
			
			log.info(">Find all bestellingen by status {} from database.",BestellingStatus.AANGEMAAKT);
			List<BestellingDataModel> inpatientsByStatus = repo.findByStatus(BestellingStatus.AANGEMAAKT.toString());
			logBestellingDataModels(inpatientsByStatus);
		
			log.info(">Save new Bestelling to database.");
			BestellingDataModel newBestelling = new BestellingDataModel(null,"Pakket","Jan Vander Broek", "7000", "kaastraat 150", "Gent", "Belgie", "Hans Landeghem", "4564", "geefstraat 4", "Geverghem", "Belgie", LocalDate.of(2020,5,4), BestellingStatus.AANGEMAAKT.name(), true, false, null );
			repo.saveAndFlush(newBestelling);
			Integer newBestellingId = newBestelling.getBestellingId();
			
			log.info(">Find Bestelling by id {} from database.", newBestellingId);
			Optional<BestellingDataModel> bestellingById= repo.findById(newBestellingId);
			  bestellingById.ifPresentOrElse( (value) -> {
			  logBestellingDataModels(Collections.unmodifiableList(Arrays.asList(value)));
			  }, () -> { logBestellingDataModels(Collections.emptyList()); } );
			  
			log.info(">Delete Bestelling by id {} from database.", newBestellingId);
			repo.deleteById(newBestellingId);
		};
	}
	
	private static void logBestellingen(List<Bestelling> bestellingen) {
		log.info("-Number of bestellingen found: {}", bestellingen.size());
		for(Bestelling bestelling : bestellingen) {
			log.info("--bestellingId {};"
					+ " Type {}, naamAfzender {}, straatAfzender {}, postcodeAfzender {},"
					+ " naamOntvanger {}, straatOntvanger {}, postcodeOntvanger {}, status {},"
					+ " spoed {}, extern {}, externeLeveringService {}."
					,
					bestelling.getBestellingId(),
					bestelling.getTypeBestelling(), bestelling.getAfzender().getNaam(), bestelling.getAfzender().getStraat(),
					bestelling.getAfzender().getPostcode(), bestelling.getOntvanger().getNaam(), bestelling.getOntvanger().getStraat(), 
				bestelling.getOntvanger().getPostcode(), bestelling.getStatus(), bestelling.isSpoed(),
				bestelling.isExtern(), bestelling.getExterneLeveringService());
		}
	}
	
	@Bean
	CommandLineRunner testBestellingRepository(BestellingRepository repo) {
		return (args) -> {
			log.info("$Testing BestellingRepository.");
			
			log.info(">Find one Bestelling by id {} from database.", 0);
			Bestelling bestellingById = repo.findOne(0);
			logBestellingen(Collections.unmodifiableList(Arrays.asList(bestellingById)));
			
			
			log.info(">Save new Bestelling to database.");
			Bestelling newbestelling = new Bestelling(null,"Pakket",new Adres("Jan Vander Broek", "7000", "kaastraat 150", "Gent", "Belgie"), new Adres("Hans Landeghem", "4564", "geefstraat 4", "Geverghem", "Belgie"), LocalDate.of(2020,5,4), BestellingStatus.AANGEMAAKT, true, false, null );
			repo.save(newbestelling);
			Integer newBestellingId = newbestelling.getBestellingId();
			
			log.info(">Find all Bestellingen with status Aangemaakt.");
			List<Bestelling> bestellingen = repo.findAllNietVerwerkt();
			logBestellingen(bestellingen);
			
		};
	}

}
