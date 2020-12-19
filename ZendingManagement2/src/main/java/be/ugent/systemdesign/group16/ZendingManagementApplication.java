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

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.ZendingStatus;
import be.ugent.systemdesign.group16.infrastructure.ZendingDataModel;
import be.ugent.systemdesign.group16.infrastructure.ZendingDataModelRepository;
@EnableAsync
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
			
			log.info(">Find all zendingen by status {} from database.",ZendingStatus.AANGEMAAKT);
			List<ZendingDataModel> zendingenByStatus = repo.findByStatus(ZendingStatus.AANGEMAAKT.toString());
			logZendingDataModels(zendingenByStatus);
		
			log.info(">Save new Zending to database.");

			ZendingDataModel newZending = 
					new ZendingDataModel(null,"Pakket",
							"Spar Nieuwkerke", "8800", "Brugsesteenweg 55", "Nieuwkerke", "Belgie",
							"Tim Deputten", "7175", "Paribenstraat 10", "Putte", "Belgie",
							"Marc Koeke", "4500", "Miljoenenstraat 11", "Sint-Denijzen", "Belgie",
							false, 
							LocalDate.of(2020,7,20), 
							ZendingStatus.AANGEMAAKT.name(), 
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
}
