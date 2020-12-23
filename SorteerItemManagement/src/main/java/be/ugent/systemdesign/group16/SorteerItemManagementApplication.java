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
import be.ugent.systemdesign.group16.application.event.EventHandler;
import be.ugent.systemdesign.group16.application.event.NieuwSorteerItemEvent;
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
	
	private static SorteerItemDataModel maakSorteerItemDataModel(AdresDataModel doel, AdresDataModel afkomst,
			AdresDataModel huidigeLocatie, String soort, boolean spoed, String status, LocalDate aanmaakDatum) {
		SorteerItemDataModel sorteerItem = new SorteerItemDataModel();
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
	
	private static SorteerItem maakSorteerItem(Adres doel, Adres afkomst,
			Adres huidigeLocatie, String soort, boolean spoed, String status, LocalDate aanmaakDatum) {
		return SorteerItem.builder()
				.doel(doel)
				.afkomst(afkomst)
				.huidigeLocatie(huidigeLocatie)
				.soort(Soort.valueOf(soort))
				.spoed(spoed)
				.status(SorteerItemStatus.valueOf(status))
				.aanmaakDatum(aanmaakDatum)
				.build();
	}
	
	private static NieuwSorteerItemEvent maakNieuwSorteerItemEvent(String typeZending, String naamAfzender,
			String postcodeAfzender, String straatAfzender, String plaatsAfzender, String landAfzender, String naamOntvanger, String postcodeOntvanger,
			String straatOntvanger, String plaatsOntvanger, String landOntvanger, String naamHuidigeLocatie, String postcodeHuidigeLocatie,
			String straatHuidigeLocatie, String plaatsHuidigeLocatie, String landHuidigeLocatie, LocalDate aanmaakDatum, boolean spoed) {
		NieuwSorteerItemEvent e = new NieuwSorteerItemEvent();
		e.setTypeZending(typeZending);
		e.setNaamAfzender(naamAfzender);
		e.setPostcodeAfzender(postcodeAfzender);
		e.setStraatAfzender(straatAfzender);
		e.setPlaatsAfzender(plaatsAfzender);
		e.setLandAfzender(landAfzender);
		e.setNaamHuidigeLocatie(naamHuidigeLocatie);
		e.setPostcodeHuidigeLocatie(postcodeHuidigeLocatie);
		e.setStraatHuidigeLocatie(straatHuidigeLocatie);
		e.setPlaatsHuidigeLocatie(plaatsHuidigeLocatie);
		e.setLandHuidigeLocatie(landHuidigeLocatie);
		e.setNaamOntvanger(naamOntvanger);
		e.setPostcodeOntvanger(postcodeOntvanger);		
		e.setStraatOntvanger(straatOntvanger);
		e.setPlaatsOntvanger(plaatsOntvanger);
		e.setLandOntvanger(landOntvanger);
		e.setSpoed(spoed);
		e.setAanmaakDatum(aanmaakDatum);
		return e;
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
			
			Integer nieuwId;
			SorteerItemDataModel sorteerItem = maakSorteerItemDataModel(
					maakAdresDataModel("Piet Kieters","8000","Stationstraat 5","Brugge","Belgie"),
					maakAdresDataModel("Klaas Klaassen","7000","Alterstraat 5","Aalter","Belgie"),
					maakAdresDataModel("Sorteercentrum Nevele","9100","nevelelaan 5","Nevele","Belgie"),
					"PAKKET", false, "IN_CENTRUM", LocalDate.now());
			log.info(">SorteerItem opslaan in de database.");
			nieuwId = repo.saveAndFlush(sorteerItem).getSorteerItemId();
			
			log.info(">Find SorteerItem with id {} in database.", nieuwId);
			Optional<SorteerItemDataModel> byIdNew = repo.findById(nieuwId);
			byIdNew.ifPresentOrElse(
				(value) -> { logSorteerItemDataModels(Collections.unmodifiableList(Arrays.asList(value))); }, 
				() -> { logSorteerItemDataModels(Collections.emptyList()); }
			);
			
			log.info(">SorteerItem met id {} verwijderen uit de database.", nieuwId);
			try {
				repo.deleteById(nieuwId);
			}
			catch(RuntimeException e) {
				log.info("Id {} not found.", nieuwId);
			}
			
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
			
			log.info(">Find SorteerItem with id 1000 in database.");
			try {
				SorteerItem byId = repo.findById(1000);
				logSorteerItems(Collections.unmodifiableList(Arrays.asList(byId)));
			}
			catch(SorteerItemNotFoundException e) {
				log.info("--id 1000 not found.");
			}
			
			Integer nieuwId;
			SorteerItem sorteerItem = maakSorteerItem(
					maakAdres("Piet Kieters","8000","Stationstraat 5","Brugge","Belgie"),
					maakAdres("Klaas Klaassen","7000","Alterstraat 5","Aalter","Belgie"),
					maakAdres("Sorteercentrum Nevele","9100","nevelelaan 5","Nevele","Belgie"),
					"PAKKET", false, "IN_CENTRUM", LocalDate.now());
			log.info(">SorteerItem opslaan in de database.");
			nieuwId = repo.save(sorteerItem);
			
			log.info(">Find SorteerItem with id {} in database.", nieuwId);
			try {
				SorteerItem byId2 = repo.findById(nieuwId);
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
			
			log.info("Na de testen de dummydata verwijderen");
			for(int i=0;i<6;i++) {
				repo.delete(i+1000);
			}
		};
	}
	
	@Bean
	CommandLineRunner testEventHandler(EventHandler handler) {
		return (args) -> {
			log.info("$Testing EventHandler.");
			
			NieuwSorteerItemEvent e = maakNieuwSorteerItemEvent("PAKKET","Koen Jansen","8000","Larestraat 5",
					"Brugge","Belgie","Kaat Klaasen","1000","Brusselsestraat 5","Brussel","Belgie", 
					"Sorteercentrum Gent","9000","Voskenslaan 5","Gent","Belgie",LocalDate.now(),false);
			log.info(">Handle NieuwSorteerItemEvent.");
			handler.handleNieuwSorteerItemEvent(e);
		};
	}
}
