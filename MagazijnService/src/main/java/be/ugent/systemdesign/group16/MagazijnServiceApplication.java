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
import be.ugent.systemdesign.group16.domain.PakketStatus;
import be.ugent.systemdesign.group16.infrastructure.PakketDataModel;
import be.ugent.systemdesign.group16.infrastructure.PakketDataModelRepository;

@SpringBootApplication
@EnableAsync
@EnableBinding(Channels.class)
public class MagazijnServiceApplication {
	private static final Logger log = LoggerFactory.getLogger(MagazijnServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MagazijnServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner populateDatabase(PakketDataModelRepository repo) {
		return (args) -> {
			log.info("Populating database");
			List<PakketDataModel> pakketten = Arrays.asList(
				new PakketDataModel(0,
						"Geert Klaasen","9100","klopperstraat 5","Sint-Niklaas","Belgie",
						"Piet klaasen","9000","grieksetraat 20","Gent","Belgie",
						"Spar Deinze","9005","Deinzestraat 5","Deinze","Belgie",
						"PAKKET",
						 false,
						 true,
						"AANGEMAAKT"),
				new PakketDataModel(1,
						"Lucia Glacia","7000","westvlstraat 9","Westegem","Belgie",
						"Carolien Haas","8404","ieperstraat 20","Ieper","Belgie",
						"Delhaize Affligem","9800","Bierstraat 24","Affligem","Belgie",
						"PAKKET",
						true
						,true,
						"AANGEMAAKT"),
				new PakketDataModel(2,
						"Kaat Kaas","1000","komstraat 55","Sint-Niklaas","Belgie",
						"Piet klaasen","9000","grieksetraat 20","Gent","Belgie",
						"Afhaalpunt Melle","9200","Melleweg 8","Melle","Belgie",
						"PAKKET",
						true
						,true,
						"AANGEMAAKT"),
				new PakketDataModel(3,
						"Kaat Kaas","1000","komstraat 55","Sint-Niklaas","Belgie",
						"Piet klaasen","9000","grieksetraat 20","Gent","Belgie",
						"Afhaalpunt Bergen","4044","Bergheuvelstraat 712","Bergen","Belgie",
						"PAKKET",
						true
						,true,
						"AANGEMAAKT")
				);
						
			pakketten.forEach(pakket -> repo.save(pakket));
			repo.flush();
			
			List<PakketDataModel> foundPakketten = repo.findAll();
			logPakketDataModels(foundPakketten);
		};
	}
	
	private static void logPakketDataModels(List<PakketDataModel> pakketten) {
		log.info("-Number of pakketten found: {}", pakketten.size());
		for(PakketDataModel pakket : pakketten) {
			log.info("--pakketId {};"
					+ " naamAfzender {}, straatAfzender {}, postcodeAfzender {},"
					+ " naamOntvanger {}, straatOntvanger {}, postcodeOntvanger {}, "
					+ " naamHuidigeLocatie {}, straatHuidigeLocatie {}, postcodeHuidigeLocatie {}, "
					+ " soort {},"
					+ " ophalenBijKlantThuis {},"
					+ " spoed {},"
					+ " status {}."
					,
					pakket.getPakketId(),
					pakket.getNaamAfzender(), pakket.getStraatAfzender(),pakket.getPostcodeAfzender(), 
					pakket.getNaamOntvanger(), pakket.getStraatOntvanger(), pakket.getPostcodeOntvanger(),
					pakket.getNaamHuidigeLocatie(), pakket.getStraatHuidigeLocatie(), pakket.getPostcodeHuidigeLocatie(),
					pakket.getSoort(),
					pakket.getOphalen(),
					pakket.getSpoed(),
					pakket.getStatus());
		}
	}
	

	@Bean
	CommandLineRunner testPakketDataModelRepository(PakketDataModelRepository repo) {
		return (args) ->{
			log.info("$Testing PakketDataModelJpaRepository.");
			  
			log.info(">Find all Pakketten from database.");
			List<PakketDataModel> pakkettenAll = repo.findAll(); 
			logPakketDataModels(pakkettenAll);
			
			log.info(">Find all pakketten by status {} from database.",PakketStatus.AANGEMAAKT);
			List<PakketDataModel> pakkettenByStatus = repo.findByStatus(PakketStatus.AANGEMAAKT.toString());
			logPakketDataModels(pakkettenByStatus);
		
			log.info(">Find all pakketten by status {} from database.",PakketStatus.VERWERKT);
			pakkettenByStatus = repo.findByStatus(PakketStatus.VERWERKT.toString());
			logPakketDataModels(pakkettenByStatus);
			
			log.info(">Save new Pakket to database.");
			PakketDataModel newPakket = new PakketDataModel(4,
					"Elon Musk","2222","Boringcompanystraat 1","Brussel","Belgie",
					"Tim Cook","5555","Applestraat 20","Knokke","Belgie",
					"Spar Deinze","9005","Deinzestraat 5","Deinze","Belgie",
					PakketStatus.AANGEMAAKT.name(), 
					 false,
					 true,
					"AANGEMAAKT");

			Integer newPakketId = repo.saveAndFlush(newPakket).getPakketId();
			
			log.info(">Find Pakket by id {} from database.", newPakketId);
			Optional<PakketDataModel> pakketById= repo.findById(newPakketId);
			pakketById.ifPresentOrElse( (value) -> {
				logPakketDataModels(Collections.unmodifiableList(Arrays.asList(value)));
			  }, () -> { logPakketDataModels(Collections.emptyList()); } );
			  
			log.info(">Delete Pakket by id {} from database.", newPakketId);
			repo.deleteById(newPakketId);
		};
	}
	
}
