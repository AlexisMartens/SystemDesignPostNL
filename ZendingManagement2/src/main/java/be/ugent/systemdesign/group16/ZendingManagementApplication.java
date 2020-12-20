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
import org.springframework.scheduling.annotation.EnableAsync;

import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.application.ZendingService;
import be.ugent.systemdesign.group16.domain.Adres;

import be.ugent.systemdesign.group16.domain.Zending;
import be.ugent.systemdesign.group16.domain.ZendingRepository;
import be.ugent.systemdesign.group16.domain.ZendingStatus;
import be.ugent.systemdesign.group16.infrastructure.ZendingDataModel;
import be.ugent.systemdesign.group16.infrastructure.ZendingDataModelRepository;

@SpringBootApplication
public class ZendingManagementApplication {
	private static final Logger log = LoggerFactory.getLogger(ZendingManagementApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ZendingManagementApplication.class, args);
	}

	private static void logZendingDataModels(List<ZendingDataModel> zendingen) {
		log.info("-Number of zendingen found: {}", zendingen.size());
		for(ZendingDataModel zending : zendingen) {
			log.info("--zendingId {};"
					+ " Type {}, naamAfzender {}, straatAfzender {}, postcodeAfzender {},"
					+ " naamOntvanger {}, straatOntvanger {}, postcodeOntvanger {}, "
					+ " ophalenBijKlantThuis {},"
					+ " naamHuidigeLocatie {}, straatHuidigeLocatie {}, postcodeHuidigeLocatie {}, "
					+ " status {},"
					+ " spoed {}, extern {}."
					,
					zending.getZendingId(),
					zending.getTypeZending(), 
					zending.getNaamAfzender(), zending.getStraatAfzender(),zending.getPostcodeAfzender(), zending.getNaamOntvanger(), zending.getStraatOntvanger(), zending.getPostcodeOntvanger(),
					zending.isOphalenBijKlantThuis(),
					zending.getNaamHuidigeLocatie(), zending.getStraatHuidigeLocatie(), zending.getPostcodeHuidigeLocatie(),
					zending.getStatus(),					
					zending.getSpoed(),
					zending.getExtern());
		}
	}
	
	@Bean
	CommandLineRunner testZendingDataModelRepository(ZendingDataModelRepository repo) {
		return (args) ->{
			log.info("$Testing ZendingDataModelJPARepository.");
			  
			log.info(">Find all Zendingen from database."); List<ZendingDataModel>
			zendingenAll = repo.findAll(); logZendingDataModels(zendingenAll);
			
			log.info(">Find all zendingen by status {} from database.",ZendingStatus.AF_TE_HALEN_IN_AFHAALPUNT);
			List<ZendingDataModel> zendingenByStatus = repo.findByStatus(ZendingStatus.AF_TE_HALEN_IN_AFHAALPUNT.toString());
			logZendingDataModels(zendingenByStatus);
		
			log.info(">Save new Zending to database.");

			ZendingDataModel newZending = 
					new ZendingDataModel(null,"Pakket",
							"Spar Nieuwkerke", "8800", "Brugsesteenweg 55", "Nieuwkerke", "Belgie",
							"Tim Deputten", "7175", "Paribenstraat 10", "Putte", "Belgie",
							"Marc Koeke", "4500", "Miljoenenstraat 11", "Sint-Denijzen", "Belgie",
							false, 
							LocalDate.of(2020,7,20), 
							ZendingStatus.AF_TE_HALEN_IN_AFHAALPUNT.name(), 
							true, 
							false);
			repo.saveAndFlush(newZending);
			Integer newZendingId = newZending.getZendingId();
			
			log.info(">Find Zending by id {} from database.", newZendingId);
			Optional<ZendingDataModel> zendingById= repo.findById(newZendingId);
			zendingById.ifPresentOrElse( (value) -> {
				logZendingDataModels(Collections.unmodifiableList(Arrays.asList(value)));
			  }, () -> { logZendingDataModels(Collections.emptyList()); } );
			  
			log.info(">Delete Zending by id {} from database.", newZendingId);
			repo.deleteById(newZendingId);
		};
	}
	
	private static void logZendingen(List<Zending> zendingen) {
		log.info("-Number of zendingen found: {}", zendingen.size());
		for(Zending zending : zendingen) {
			log.info("--zendingId {};"
					+ " Type {}, naamAfzender {}, straatAfzender {}, postcodeAfzender {},"
					+ " naamOntvanger {}, straatOntvanger {}, postcodeOntvanger {}, "
					+ " ophalenBijKlantThuis {},"
					+ " naamHuidigeLocatie {}, straatHuidigeLocatie {}, postcodeHuidigeLocatie {}, "
					+ " status {},"
					+ " spoed {}, extern {}."
					,
					zending.getZendingId(),
					zending.getTypeZending(), zending.getAfzender().getNaam(), zending.getAfzender().getStraat(),zending.getAfzender().getPostcode(), 
					zending.getOntvanger().getNaam(), zending.getOntvanger().getStraat(), zending.getOntvanger().getPostcode(), 
					zending.isOphalenBijKlantThuis(),
					zending.getHuidigeLocatie().getNaam(), zending.getHuidigeLocatie().getStraat(), zending.getHuidigeLocatie().getPostcode(), 
					zending.getStatus(),
					zending.isSpoed(), zending.isExtern());
		}
	}
	
	@Bean
	CommandLineRunner testZendingRepository(ZendingRepository repo) {
		return (args) -> {
			log.info("$Testing ZendingRepository.");
			
			/*log.info(">Find one Zending by id {} from database.", 0);
			Zending zendingById = repo.findOne(0);
			logZendingen(Collections.unmodifiableList(Arrays.asList(zendingById)));
			*/
			log.info(">Save new Zending to database.");
		
			Zending newZending = new Zending("Pakket",
					"Spar GSP", "8800", "Denenstraat 85", "Gent", "Belgie",
					"Karel Veke", "7000", "Koeienstraat 10", "Merelen", "Belgie",
					"Nick Heldens", "3330", "Paardenstraat 47", "Eergemstraat 80", "Belgie",			
					false, 
					true, 
					false);
			repo.save(newZending);
			Integer newZendingId = newZending.getZendingId();
			
			log.info(">Find all Zendingen with status AfTeHalenBijAfhaalPunt.");
			List<Zending> zendingen = repo.findAllAfTeHalen();
			logZendingen(zendingen);
			
			log.info(">Find all Zendingen with status OpTeHalenBijKlant.");
			List<Zending> zendingenOpTeHalen = repo.findAllOpTeHalenBijKlant();
			logZendingen(zendingenOpTeHalen);
			
			log.info(">Find all Zendingen with status AfgehaaldBijAfhaalPunt.");
			List<Zending> zendingenOpgehaald = repo.findAllAfgehaald();
			logZendingen(zendingenOpgehaald);
			
		};
	}
	
	private static void logResponse(Response response) {
		log.info("-response status[{}] message[{}]", response.status, response.message);
	}

	@Bean
	CommandLineRunner testZendingService(ZendingService service) {
		return (args) -> {
			log.info("$Testing ZendingService.");
			
			log.info(">Bevestig aankomst nieuwe zending");
			Zending newZending = new Zending("Pakket",
					"Spar GSP", "8800", "Denenstraat 85", "Gent", "Belgie",
					"Karel Veke", "7000", "Koeienstraat 10", "Merelen", "Belgie",
					"Nick Heldens", "3330", "Paardenstraat 47", "Male", "Belgie",			
					false, 
					true, 
					false);
			
			Response response = service.bevestigAankomstNieuweZending(newZending, newZending.getHuidigeLocatie().getNaam(), newZending.getHuidigeLocatie().getPostcode(),
					newZending.getHuidigeLocatie().getStraat(), newZending.getHuidigeLocatie().getPlaats(), newZending.getHuidigeLocatie().getLand());
			logResponse(response);
			
			log.info(">Bevestig aankomst nieuwe zending 2");
			newZending = new Zending("Pakket",
					"Spar Geluwe", "8100", "Duinenweg 2", "Geluwe", "Belgie",
					"Charlie Chaplin", "4000", "Ritsstraat 55", "Brussel", "Belgie",
					"George Washington", "2999", "Wittehuisstraat 1", "Aarlen", "Belgie",			
					true, 
					true, 
					false);
			response = service.bevestigAankomstNieuweZending(newZending, newZending.getHuidigeLocatie().getNaam(), newZending.getHuidigeLocatie().getPostcode(),
					newZending.getHuidigeLocatie().getStraat(), newZending.getHuidigeLocatie().getPlaats(), newZending.getHuidigeLocatie().getLand());
			logResponse(response);
			
			log.info(">Bevestig aankomst nieuwe zending 3");
			newZending = new Zending("Pakket",
					"Proxy Delhaize Zulte", "7800", "Meensestraat 125", "Zulte", "Belgie",
					"Ward Kraken", "3000", "Vanbullenweg 70", "Leuven", "Belgie",
					"Jacque Marles", "1000", "Boulangerieweg 4", "Bergen", "Belgie",			
					true, 
					true, 
					false);
			response = service.bevestigAankomstNieuweZending(newZending, newZending.getHuidigeLocatie().getNaam(), newZending.getHuidigeLocatie().getPostcode(),
					newZending.getHuidigeLocatie().getStraat(), newZending.getHuidigeLocatie().getPlaats(), newZending.getHuidigeLocatie().getLand());
			logResponse(response);
			
			
			log.info(">bevestigAfhalen zending voor id {}", newZending.getZendingId());
			response = service.bevestigAfhalen(newZending.getZendingId());
			
			log.info(">bevestigAfhalen zending voor id {} (fail)",1000);
			response = service.bevestigAfhalen(1000);
			logResponse(response);
		};
	}
}
