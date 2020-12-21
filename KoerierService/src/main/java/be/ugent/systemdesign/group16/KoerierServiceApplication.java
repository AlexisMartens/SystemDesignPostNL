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

import be.ugent.systemdesign.group16.domain.Koerier;
import be.ugent.systemdesign.group16.domain.KoerierRepository;
import be.ugent.systemdesign.group16.infrastructure.KoerierDataModel;
import be.ugent.systemdesign.group16.infrastructure.KoerierDataModelJpaRepository;

@SpringBootApplication
public class KoerierServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(KoerierServiceApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(KoerierServiceApplication.class, args);
	}
	
	private static void logKoerierDataModels(List<KoerierDataModel> koeriers) {
		log.info("-Number of koeriers found: {}", koeriers.size());
		for(KoerierDataModel koerier : koeriers) {
			log.info("--koerierId {}       ,  naam {}         , postcodeRonde {}          , vervoercapaciteit {}          , aantal ophaalOrders {}"
					,koerier.getKoerierId(), koerier.getNaam(), koerier.getPostcodeRonde(), koerier.getVervoercapaciteit()
					//,koerier.getOrders().size()
					);
		}
	}
	
	@Bean
	CommandLineRunner testKoerierDataModelJpaRepository(KoerierDataModelJpaRepository repo) {
		return (args) ->{
			log.info("$Testing KoerierDataModelJPARepository.");
			  
			log.info(">Find all Koeriers from database."); 
			List<KoerierDataModel> koeriersAll = repo.findAll(); 
			logKoerierDataModels(koeriersAll);
			
			log.info(">Find all koeriers by postcodeRonde {} from database.",9000);
			List<KoerierDataModel> koerierByPostcodeRonde = repo.findByPostcodeRonde("9000");
			logKoerierDataModels(koerierByPostcodeRonde);
		
			log.info(">Save new Koerier to database.");
			KoerierDataModel newKoerier = new KoerierDataModel(10,"Bert","9900",50);
			repo.saveAndFlush(newKoerier);
			Integer newKoerierId = newKoerier.getKoerierId();
			
			log.info(">Find Koerier by id {} from database.", newKoerierId);
			Optional<KoerierDataModel> koerierById= repo.findById(newKoerierId);
			koerierById.ifPresentOrElse( (value) -> {
				logKoerierDataModels(Collections.unmodifiableList(Arrays.asList(value)));
			  }, () -> { logKoerierDataModels(Collections.emptyList()); } );
			  
			log.info(">Delete Bestelling by id {} from database.", newKoerierId);
			repo.deleteById(newKoerierId);
		};
	}
	
	
	private static void logKoeriers(List<Koerier> koeriers) {
		log.info("-Number of inpatients found: {}", koeriers.size());
		for(Koerier koerier : koeriers) {
			log.info("--koerierId {}       ,  naam {}         , postcodeRonde {}          , vervoercapaciteit {}          , aantal ophaalOrders {}"
					,koerier.getKoerierId(), koerier.getNaam(), koerier.getPostcodeRonde(), koerier.getVervoercapaciteit()
					//,koerier.getOrders().size()
					);
		}
	}
	
	@Bean
	CommandLineRunner testKoerierRepository(KoerierRepository repo) {
		return (args) -> {
			log.info("$Testing KoerierRepository.");
			
			log.info(">Find one koerier by id {} from database.", 1);
			Koerier koerierById = repo.findOne(1);
			logKoeriers(Collections.unmodifiableList(Arrays.asList(koerierById)));
			
			Integer newKoerierId = 5;
			log.info(">Save new koerier with id {} to database.", newKoerierId);
			Koerier newKoerier = new Koerier(newKoerierId,"Bert","9900",50);
			repo.save(newKoerier);
			
			log.info(">Find all koeriers that have postcodeRonde {}", "9000");
			List<Koerier> koeriers = repo.findByPostcodeRonde("9000");
			logKoeriers(koeriers);
		};
	}
	

}
