package be.ugent.systemdesign.group16;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import be.ugent.systemdesign.group16.API.messaging.Channels;
import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.SorteerCentrum;
import be.ugent.systemdesign.group16.domain.SorteerOrder;
import be.ugent.systemdesign.group16.domain.Sorteerder;
import be.ugent.systemdesign.group16.domain.SorteerderRepository;
import be.ugent.systemdesign.group16.domain.SorteerderStatus;
import be.ugent.systemdesign.group16.infrastructure.AdresDataModel;
import be.ugent.systemdesign.group16.infrastructure.SorteerCentrumDataModel;
import be.ugent.systemdesign.group16.infrastructure.SorteerCentrumDataModelJpaRepository;
import be.ugent.systemdesign.group16.infrastructure.SorteerOrderDataModel;
import be.ugent.systemdesign.group16.infrastructure.SorteerderDataModel;
import be.ugent.systemdesign.group16.infrastructure.SorteerderDataModelJpaRepository;

@SpringBootApplication
@EnableBinding(Channels.class)
public class SorteerServiceApplication {
	
	private static final Logger log = LoggerFactory.getLogger(SorteerServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SorteerServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner testSorteerCentrumDataModelJpaRepository(SorteerCentrumDataModelJpaRepository repo){
		return (args) -> {
			log.info("$Testing SorteerCentrumDataModelJpaRepository.");
			
			log.info(">Saving new SorteerCentrumDataModel.");
			SorteerCentrumDataModel gent = new SorteerCentrumDataModel();
			gent.setAdres(new AdresDataModel("Sorteercentrum Gent","9000","Gentstraat 10","Gent","Belgie"));
			
			SorteerCentrumDataModel nevele = new SorteerCentrumDataModel();
			nevele.setAdres(new AdresDataModel("Sorteercentrum Nevele","9100","Nevelestraat 10","Nevele","Belgie"));
			nevele.setVolgendeLocatie(gent.getAdres());
			
			SorteerCentrumDataModel brugge = new SorteerCentrumDataModel();
			brugge.setAdres(new AdresDataModel("Sorteercentrum Brugge","8000","Bruggestraat 10","Brugge","Belgie"));
			brugge.setVolgendeLocatie(nevele.getAdres());
			
			gent.setVolgendeLocatie(brugge.getAdres());
			
			repo.save(gent);
			repo.save(nevele);
			repo.save(brugge);
			
			log.info(">Find all SorteerCentrumDataModel.");
			logSorteerCentrumDataModels(repo.findAll());
		};
	}
	

	@Bean
	CommandLineRunner testSorteerderDataModelJpaRepository(SorteerderDataModelJpaRepository repo, SorteerCentrumDataModelJpaRepository centrumRepo){
		return (args) -> {
			log.info("$Testing SorteerderDataModelJpaRepository.");
			
			log.info(">Saving new SorteerderDataModel.");
			SorteerderDataModel gert = new SorteerderDataModel();
			gert.setNaam("Gert");
			gert.setStatus(SorteerderStatus.IDLE.name());
			centrumRepo.findById(1).ifPresent(centrum -> gert.setWerkLocatie(centrum));
			gert.setOrder(new SorteerOrderDataModel());
			repo.save(gert);
			
			SorteerderDataModel nick = new SorteerderDataModel();
			nick.setNaam("Nick");
			nick.setStatus(SorteerderStatus.IDLE.name());
			centrumRepo.findById(2).ifPresent(centrum -> nick.setWerkLocatie(centrum));
			nick.setOrder(new SorteerOrderDataModel());
			repo.save(nick);
			
			SorteerderDataModel bas = new SorteerderDataModel();
			bas.setNaam("Bas");
			bas.setStatus(SorteerderStatus.IDLE.name());
			centrumRepo.findById(3).ifPresent(centrum -> bas.setWerkLocatie(centrum));
			bas.setOrder(new SorteerOrderDataModel());
			repo.save(bas);
			
			log.info(">Find all SorteerCentrumDataModel.");
			logSorteerderDataModels(repo.findAll());
		};
	}
	
	@Bean
	CommandLineRunner testSorteerderRepository(SorteerderRepository repo){
		return (args) -> {
			log.info("$Testing SorteerderRepository.");
			
			log.info(">Find all SorteerCentrumDataModel in Gent.");
			logSorteerders(repo.findIdleSorteerdersAtCentrum(new Adres("Sorteercentrum Gent","9000","Gentstraat 10","Gent","Belgie")));
			
			log.info(">Saving new Sorteerder");
			SorteerCentrum oostende = new SorteerCentrum();
			oostende.setLocatieId(1000);
			oostende.setEigenLocatie(new Adres("Sorteercentrum Oostende","8500","Oostendestraat 10","Oostende","Belgie"));
			oostende.setVolgendeLocatie(new Adres("Sorteercentrum Gent","9000","Gentstraat 10","Gent","Belgie"));
			repo.save(oostende);
			
			Sorteerder oscar = new Sorteerder();
			oscar.setSorteerderId(123);
			oscar.setNaam("Oscar");
			oscar.setOrder(new SorteerOrder());
			oscar.setStatus(SorteerderStatus.IDLE);
			oscar.setWerkLocatie(oostende);
			repo.save(oscar);
			
			log.info(">Find all Sorteerders.");
			logSorteerders(repo.findAll());
		};
	}
	
	private static void logSorteerCentrumDataModels(List<SorteerCentrumDataModel> l) {
		log.info("- {} SorteerCentrumDataModels gevonden.", l.size());
		for(SorteerCentrumDataModel i: l) {
			log.info("-- id {} adres {} volgendeAdres {}.", i.getLocatieId(), i.getAdres().getNaam(), i.getVolgendeLocatie().getNaam());
		}
	}
	
	private static void logSorteerderDataModels(List<SorteerderDataModel> l) {
		log.info("- {} SorteerderDataModel gevonden.", l.size());
		for(SorteerderDataModel i: l) {
			log.info("-- id {} naam {} werk {}.", i.getSorteerderId(), i.getNaam(), i.getWerkLocatie().getAdres().getNaam());
		}
	}
	
	private static void logSorteerders(List<Sorteerder> l) {
		log.info("- {} SorteerderDataModel gevonden.", l.size());
		for(Sorteerder i: l) {
			log.info("-- id {} naam {} werk {}.", i.getSorteerderId(), i.getNaam(), i.getWerkLocatie().getEigenLocatie().getNaam());
		}
	}
}
