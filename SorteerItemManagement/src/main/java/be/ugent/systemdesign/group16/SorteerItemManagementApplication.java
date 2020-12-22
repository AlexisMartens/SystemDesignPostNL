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
import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Soort;
import be.ugent.systemdesign.group16.domain.SorteerItem;
import be.ugent.systemdesign.group16.domain.SorteerItemNotFoundException;
import be.ugent.systemdesign.group16.domain.SorteerItemRepository;
import be.ugent.systemdesign.group16.domain.SorteerItemStatus;
import be.ugent.systemdesign.group16.infrastructure.AdresDataModel;
import be.ugent.systemdesign.group16.infrastructure.SorteerItemDataModel;
import be.ugent.systemdesign.group16.infrastructure.SorteerItemDataModelJpaRepository;

@EnableBinding(Channels.class)
@SpringBootApplication
public class SorteerItemManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SorteerItemManagementApplication.class, args);
	}
	
	private static final Logger log = LoggerFactory.getLogger(SorteerItemManagementApplication.class);
	
	private static void logSorteerItemDataModels(List<SorteerItemDataModel> items) {
		log.info("-{} sorteerItems gevonden.", items.size());
		for(SorteerItemDataModel item : items) {
			log.info("--id {} doel {} afkomst {} huidigeLocatie {} soort {} spoed {} status {} aangemaakt {}.",
					item.getSorteerItemId(), item.getDoel().getNaam(), item.getAfkomst().getNaam(), item.getHuidigeLocatie().getNaam(),
					item.getSoort(), item.isSpoed(), item.getStatus(), item.getAanmaakDatum());
		}
	}
	
	private static void logSorteerItems(List<SorteerItem> items) {
		log.info("-{} sorteerItems gevonden.", items.size());
		for(SorteerItem item : items) {
			log.info("--id {} doel {} afkomst {} huidigeLocatie {} soort {} spoed {} status {} aangemaakt {}.",
					item.getSorteerItemId(), item.getDoel().getNaam(), item.getAfkomst().getNaam(), item.getHuidigeLocatie().getNaam(),
					item.getSoort(), item.isSpoed(), item.getStatus(), item.getAanmaakDatum());
		}
	}
	
	private static AdresDataModel maakAdresDataModel(String naam, String postcode, String straat, String plaats, String land) {
		AdresDataModel adres = new AdresDataModel();
		adres.setNaam(naam);
		adres.setPostcode(postcode);
		adres.setStraat(straat);
		adres.setPlaats(plaats);
		adres.setLand(land);
		return adres;
	}
	
	private static SorteerItemDataModel maakSorteerItemDataModel(Integer sorteerItemId, AdresDataModel doel, AdresDataModel afkomst,
			AdresDataModel huidigeLocatie, String soort, boolean spoed, String status, LocalDate aanmaakDatum) {
		SorteerItemDataModel sorteerItem = new SorteerItemDataModel();
		sorteerItem.setSorteerItemId(sorteerItemId);
		sorteerItem.setDoel(doel);
		sorteerItem.setAfkomst(afkomst);
		sorteerItem.setHuidigeLocatie(huidigeLocatie);
		sorteerItem.setSoort(soort);
		sorteerItem.setSpoed(spoed);
		sorteerItem.setStatus(status);
		sorteerItem.setAanmaakDatum(aanmaakDatum);
		return sorteerItem;
	}
	
	private static Adres maakAdres(String naam, String postcode, String straat, String plaats, String land) {
		return Adres.builder()
				.naam(naam)
				.postcode(postcode)
				.straat(straat)
				.plaats(plaats)
				.land(land)
				.build();
	}
	
	private static SorteerItem maakSorteerItem(Integer sorteerItemId, Adres doel, Adres afkomst,
			Adres huidigeLocatie, String soort, boolean spoed, String status, LocalDate aanmaakDatum) {
		return SorteerItem.builder()
				.sorteerItemId(sorteerItemId)
				.doel(doel)
				.afkomst(afkomst)
				.huidigeLocatie(huidigeLocatie)
				.soort(Soort.valueOf(soort))
				.spoed(spoed)
				.status(SorteerItemStatus.valueOf(status))
				.aanmaakDatum(aanmaakDatum)
				.build();
	}
	
	@Bean
	CommandLineRunner testSorteerItemDataModelJpaRepository(SorteerItemDataModelJpaRepository repo) {
		return (args) -> {
			log.info("$Testing SorteerItemDataModelJpaRepository.");
			
			log.info(">Find all SorteerItems in database.");
			List<SorteerItemDataModel> allSorteerItems = repo.findAll();
			logSorteerItemDataModels(allSorteerItems);
			
			log.info(">Find SorteerItem with id 0 in database.");
			Optional<SorteerItemDataModel> byId = repo.findById(0);
			byId.ifPresentOrElse(
				(value) -> { logSorteerItemDataModels(Collections.unmodifiableList(Arrays.asList(value))); }, 
				() -> { logSorteerItemDataModels(Collections.emptyList()); }
			);
			
			
			AdresDataModel nevele = maakAdresDataModel("Sorteercentrum Nevele","9100","nevelelaan 5","Nevele","Belgie");
			log.info(">Find all SorteerItems in {}.", nevele.getNaam());
			List<SorteerItemDataModel> sorteerItemsPerLocatie = repo.findByHuidigeLocatie(nevele);
			logSorteerItemDataModels(sorteerItemsPerLocatie);
			
			Integer nieuwId = 6;
			SorteerItemDataModel sorteerItem = maakSorteerItemDataModel(nieuwId,
					maakAdresDataModel("Piet Kieters","8000","Stationstraat 5","Brugge","Belgie"),
					maakAdresDataModel("Klaas Klaassen","7000","Alterstraat 5","Aalter","Belgie"),
					maakAdresDataModel("Sorteercentrum Nevele","9100","nevelelaan 5","Nevele","Belgie"),
					"PAKKET", false, "IN_CENTRUM", LocalDate.now());
			log.info(">SorteerItem met id {} opslaan in de database.", nieuwId);
			repo.saveAndFlush(sorteerItem);
			
			log.info(">Find SorteerItem with id {} in database.", nieuwId);
			Optional<SorteerItemDataModel> byIdNew = repo.findById(nieuwId);
			byIdNew.ifPresentOrElse(
				(value) -> { logSorteerItemDataModels(Collections.unmodifiableList(Arrays.asList(value))); }, 
				() -> { logSorteerItemDataModels(Collections.emptyList()); }
			);
			
			log.info(">SorteerItem met id {} verwijderen uit de database.", nieuwId);
			repo.deleteById(nieuwId);
			
			log.info(">Find Deleted SorteerItem with id {} in database.", nieuwId);
			Optional<SorteerItemDataModel> niets = repo.findById(nieuwId);
			niets.ifPresentOrElse(
				(value) -> { logSorteerItemDataModels(Collections.unmodifiableList(Arrays.asList(value))); }, 
				() -> { logSorteerItemDataModels(Collections.emptyList()); }
			);
		};
	}
	
	@Bean
	CommandLineRunner testSorteerItemRepository(SorteerItemRepository repo) {
		return (args) -> {
			log.info("$Testing SorteerItemRepository.");
			
			log.info(">Find all SorteerItems in database.");
			List<SorteerItem> allSorteerItems = repo.findAll();
			logSorteerItems(allSorteerItems);
			
			log.info(">Find SorteerItem with id 0 in database.");
			try {
				SorteerItem byId = repo.findById(0);
				logSorteerItems(Collections.unmodifiableList(Arrays.asList(byId)));
			}
			catch(SorteerItemNotFoundException e) {
				log.info("--id 0 not found.");
			}
			
			Integer nieuwId = 6;
			SorteerItem sorteerItem = maakSorteerItem(nieuwId,
					maakAdres("Piet Kieters","8000","Stationstraat 5","Brugge","Belgie"),
					maakAdres("Klaas Klaassen","7000","Alterstraat 5","Aalter","Belgie"),
					maakAdres("Sorteercentrum Nevele","9100","nevelelaan 5","Nevele","Belgie"),
					"PAKKET", false, "IN_CENTRUM", LocalDate.now());
			log.info(">SorteerItem met id {} opslaan in de database.", nieuwId);
			repo.save(sorteerItem);
			
			log.info(">Find SorteerItem with id {} in database.", nieuwId);
			try {
				SorteerItem byId2 = repo.findById(0);
				logSorteerItems(Collections.unmodifiableList(Arrays.asList(byId2)));
			}
			catch(SorteerItemNotFoundException e) {
				log.info("--id {} not found.", nieuwId);
			}
			
			log.info(">SorteerItem met id {} verwijderen uit de database.", nieuwId);
			repo.delete(nieuwId);
			
			log.info(">Find SorteerItem with id {} in database. Should be deleted.", nieuwId);
			try {
				SorteerItem byId3 = repo.findById(nieuwId);
				logSorteerItems(Collections.unmodifiableList(Arrays.asList(byId3)));
			}
			catch(SorteerItemNotFoundException e) {
				log.info("--id {} not found.", nieuwId);
			}
		};
	}
}
