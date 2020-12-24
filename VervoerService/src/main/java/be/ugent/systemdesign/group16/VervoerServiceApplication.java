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

import be.ugent.systemdesign.group16.API.messaging.Channels;
import be.ugent.systemdesign.group16.domain.VervoerStatus;
import be.ugent.systemdesign.group16.domain.Vervoerder;
import be.ugent.systemdesign.group16.domain.VervoerderRepository;
import be.ugent.systemdesign.group16.infrastructure.VervoerderDataModel;
import be.ugent.systemdesign.group16.infrastructure.VervoerderDataModelJpaRepository;

@EnableBinding(Channels.class)
@SpringBootApplication
public class VervoerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VervoerServiceApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(VervoerServiceApplication.class);
	
	private static void logVervoerderDataModels(List<VervoerderDataModel> items) {
		log.info("-{} VervoerderDataModels gevonden.", items.size());
		for(VervoerderDataModel item : items) {
			log.info("--id {} naam {} status {}.",
					item.getVervoerderId(), item.getNaam(), item.getStatus());
		}
	}
	
	private static void logVervoerders(List<Vervoerder> items) {
		log.info("-{} VervoerderDataModels gevonden.", items.size());
		for(Vervoerder item : items) {
			log.info("--id {} naam {} status {}.",
					item.getVervoerderId(), item.getNaam(), item.getStatus());
		}
	}
	
	@Bean
	CommandLineRunner testVervoerderDataModelJpaRepository(VervoerderDataModelJpaRepository repo) {
		return (args) -> {
			log.info("$Testing VervoerderDataModelJpaRepository.");
			
			log.info(">Find all VervoerderDataModel in database.");
			List<VervoerderDataModel> allSorteerItems = repo.findAll();
			logVervoerderDataModels(allSorteerItems);
			
			log.info(">Find VervoerderDataModel with id 1000 in database.");
			Optional<VervoerderDataModel> byId = repo.findById(0);
			byId.ifPresentOrElse(
				(value) -> { logVervoerderDataModels(Collections.unmodifiableList(Arrays.asList(value))); }, 
				() -> { logVervoerderDataModels(Collections.emptyList()); }
			);
			
			Integer nieuwId;
			VervoerderDataModel vervoerderDataModel = new VervoerderDataModel();
			vervoerderDataModel.setNaam("Karel");
			vervoerderDataModel.setStatus(VervoerStatus.IDLE.name());
			log.info(">Vervoerder opslaan in de database.");
			nieuwId = repo.saveAndFlush(vervoerderDataModel).getVervoerderId();
			
			log.info(">Find VervoerderDataModel with id {} in database.", nieuwId);
			Optional<VervoerderDataModel> byIdNew = repo.findById(nieuwId);
			byIdNew.ifPresentOrElse(
				(value) -> { logVervoerderDataModels(Collections.unmodifiableList(Arrays.asList(value))); }, 
				() -> { logVervoerderDataModels(Collections.emptyList()); }
			);
			
			log.info(">SorteerItem met id {} verwijderen uit de database.", nieuwId);
			try {
				repo.deleteById(nieuwId);
			}
			catch(RuntimeException e) {
				log.info("Id {} not found.", nieuwId);
			}
			
			log.info(">Find Deleted SorteerItem with id {} in database.", nieuwId);
			Optional<VervoerderDataModel> niets = repo.findById(nieuwId);
			niets.ifPresentOrElse(
				(value) -> { logVervoerderDataModels(Collections.unmodifiableList(Arrays.asList(value))); }, 
				() -> { logVervoerderDataModels(Collections.emptyList()); }
			);
		};
	}
	
	@Bean
	CommandLineRunner testVervoerderRepository(VervoerderRepository repo) {
		return (args) -> {
			log.info("$Testing SorteerItemRepository.");
			
			Vervoerder v = new Vervoerder();
			v.setNaam("Sjarel");
			v.setStatus(VervoerStatus.IDLE);
			log.info(">Save Vervoerder {}.", v.getNaam());
			v.setVervoerderId(repo.save(v));
			repo.save(v);

			log.info(">Find all Idle vervoerders in database.");
			List<Vervoerder> allVervoerders = repo.findIdleVervoerders();
			logVervoerders(allVervoerders);
		};
	}
}
