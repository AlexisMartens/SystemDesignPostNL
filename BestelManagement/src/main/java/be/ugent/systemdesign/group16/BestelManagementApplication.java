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

import be.ugent.systemdesign.group16.infrastructure.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import be.ugent.systemdesign.group16.API.messaging.Channels;
import be.ugent.systemdesign.group16.application.BestelService;
import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.domain.*;

@EnableSwagger2
@EnableAsync
@EnableBinding(Channels.class)
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
			BestellingDataModel newBestelling = new BestellingDataModel(null,"Pakket","Jan Vander Broek", "7000", "kaastraat 150", "Gent", "Belgie", "Hans Landeghem", "4564", "geefstraat 4", "Geverghem", "Belgie", false, LocalDate.of(2020,5,4), BestellingStatus.AANGEMAAKT.name(), true, false, null );
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
			
			//log.info(">Find one Bestelling by id {} from database.", 0);
			//Bestelling bestellingById = repo.findOne(0);
			//logBestellingen(Collections.unmodifiableList(Arrays.asList(bestellingById)));
			
			log.info(">Save new Bestelling to database.");
			Bestelling newbestelling = new Bestelling(null,"Pakket",new Adres("Jan Vander Broek", "7000", "kaastraat 150", "Gent", "Belgie"), new Adres("Hans Landeghem", "4564", "geefstraat 4", "Geverghem", "Belgie"), false, LocalDate.of(2020,5,4), BestellingStatus.AANGEMAAKT, true, false, null );
			repo.save(newbestelling);
			Integer newBestellingId = newbestelling.getBestellingId();
			
			log.info(">Find all Bestellingen with status Aangemaakt.");
			List<Bestelling> bestellingen = repo.findAllNietVerwerkt();
			logBestellingen(bestellingen);
			
		};
	}
	
	private static void logResponse(Response response) {
		log.info("-response status[{}] message[{}]", response.status, response.message);
	}

	@Bean
	CommandLineRunner testBestelService(BestelService service) {
		return (args) -> {
			log.info("$Testing BestelService.");
			
			log.info(">Maak nieuwe Bestelling");
			Bestelling newBestelling = new Bestelling(null,"Pakket",new Adres("Jan Vander Broek", "7000", "kaastraat 150", "Gent", "Belgie"), new Adres("Hans Landeghem", "4564", "geefstraat 4", "Geverghem", "Belgie"), false, LocalDate.of(2020,5,4), BestellingStatus.AANGEMAAKT, true, true, ExterneLeveringService.PHARMAANDCARE );
			Response response = service.plaatsBestelling(newBestelling);
			logResponse(response);
			
			log.info(">Maak nieuwe Bestelling");
			 newBestelling = new Bestelling(null,"Pakket",new Adres("Jan Vander Broek", "7000", "kaastraat 150", "Gent", "Belgie"), new Adres("Hans Landeghem", "4564", "geefstraat 4", "Geverghem", "Belgie"), false, LocalDate.of(2020,5,4), BestellingStatus.AANGEMAAKT, true, true, ExterneLeveringService.EXTRAATHOME  );
			response = service.plaatsBestelling(newBestelling);
			logResponse(response);
			
			log.info(">Maak nieuwe Bestelling");
			newBestelling = new Bestelling(null,"Pakket",new Adres("Jan Vander Broek", "7000", "kaastraat 150", "Gent", "Belgie"), new Adres("Hans Landeghem", "4564", "geefstraat 4", "Geverghem", "Belgie"), false, LocalDate.of(2020,5,4), BestellingStatus.AANGEMAAKT, true, false, null );
			response = service.plaatsBestelling(newBestelling);
			logResponse(response);
			
			log.info(">maak retour bestelling voor id {}", newBestelling.getBestellingId());
			response = service.plaatsRetour(newBestelling.getBestellingId());
			logResponse(response);
		};
	}
	
}
