package be.ugent.systemdesign.group16;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
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
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.application.ZendingService;
import be.ugent.systemdesign.group16.application.event.BevestigAfleverenZendingEvent;
import be.ugent.systemdesign.group16.application.event.BevestigOphalenZendingEvent;
import be.ugent.systemdesign.group16.application.event.EventHandler;
import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.API.messaging.Channels;

import be.ugent.systemdesign.group16.domain.Zending;
import be.ugent.systemdesign.group16.domain.ZendingRepository;
import be.ugent.systemdesign.group16.domain.ZendingStatus;
import be.ugent.systemdesign.group16.infrastructure.ZendingDataModel;
import be.ugent.systemdesign.group16.infrastructure.ZendingDataModelRepository;
@EnableAsync
@EnableBinding(Channels.class)
@SpringBootApplication
public class ZendingManagementApplication {
	private static final Logger log = LoggerFactory.getLogger(ZendingManagementApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ZendingManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner populateDatabase(ZendingDataModelRepository repo) {
		return (args) -> {
			log.info("Populating database");
			List<ZendingDataModel> zendingen = Arrays.asList(
				new ZendingDataModel(0, "PAKKET",
						"Spar Deinze","9005","Deinzestraat 5","Deinze","Belgie","Geert Klaasen","9100","klopperstraat 5","Sint-Niklaas","Belgie",
						"Piet klaasen","9000","grieksetraat 20","Gent","Belgie",
						true, LocalDate.now(),
						"KLAAR_OM_OP_TE_HALEN",false),
				new ZendingDataModel(1, "PAKKET",
						"Delhaize Affligem","9800","Bierstraat 24","Affligem","Belgie","Lucia Glacia","7000","westvlstraat 9","Westegem","Belgie",
						"Carolien Haas","8404","ieperstraat 20","Ieper","Belgie",
						true, LocalDate.now(),
						"KLAAR_OM_OP_TE_HALEN",false),
				new ZendingDataModel(2, "PAKKET",
						"Afhaalpunt Melle","9200","Melleweg 8","Melle","Belgie","Kaat Kaas","1000","komstraat 55","Sint-Niklaas","Belgie",
						"Piet klaasen","9000","grieksetraat 20","Gent","Belgie",
						true, LocalDate.now(),
						"KLAAR_OM_OP_TE_HALEN",false),
				new ZendingDataModel(3, "PAKKET",
						"Afhaalpunt Bergen","4044","Bergheuvelstraat 712","Bergen","Belgie","Kaat Kaas","1000","komstraat 55","Sint-Niklaas","Belgie",
						"Piet klaasen","9000","grieksetraat 20","Gent","Belgie",
						false, LocalDate.now(),
						"KLAAR_OM_OP_TE_HALEN",false)
				);
						
			zendingen.forEach(zending -> repo.save(zending));
			repo.flush();
			
			List<ZendingDataModel> foundZendingen = repo.findAll();
			logZendingDataModels(foundZendingen);
		};
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
					+ " spoed {}."
					,
					zending.getZendingId(),
					zending.getTypeZending(), 
					zending.getNaamAfzender(), zending.getStraatAfzender(),zending.getPostcodeAfzender(), zending.getNaamOntvanger(), zending.getStraatOntvanger(), zending.getPostcodeOntvanger(),
					zending.isOphalenBijKlantThuis(),
					zending.getNaamHuidigeLocatie(), zending.getStraatHuidigeLocatie(), zending.getPostcodeHuidigeLocatie(),
					zending.getStatus(),					
					zending.getSpoed());
		}
	}
	
	@Bean
	CommandLineRunner testZendingDataModelRepository(ZendingDataModelRepository repo) {
		return (args) ->{
			log.info("$Testing ZendingDataModelJPARepository.");
			  
			log.info(">Find all Zendingen from database.");
			List<ZendingDataModel>	zendingenAll = repo.findAll(); 
			logZendingDataModels(zendingenAll);
			
			log.info(">Find all zendingen by status {} from database.",ZendingStatus.KLAAR_OM_OP_TE_HALEN);
			List<ZendingDataModel> zendingenByStatus = repo.findByStatus(ZendingStatus.KLAAR_OM_OP_TE_HALEN.toString());
			logZendingDataModels(zendingenByStatus);
		
			log.info(">Find all zendingen by status {} from database.",ZendingStatus.AANGEMAAKT);
			zendingenByStatus = repo.findByStatus(ZendingStatus.AANGEMAAKT.toString());
			logZendingDataModels(zendingenByStatus);
			
			log.info(">Save new Zending to database.");

			ZendingDataModel newZending = 
					new ZendingDataModel(9,"PAKKET",
							"Spar Nieuwkerke", "8800", "Brugsesteenweg 55", "Nieuwkerke", "Belgie",
							"Tim Deputten", "7175", "Paribenstraat 10", "Putte", "Belgie",
							"Marc Koeke", "4500", "Miljoenenstraat 11", "Sint-Denijzen", "Belgie",
							false, 
							LocalDate.of(2020,7,20), 
							ZendingStatus.KLAAR_OM_OP_TE_HALEN.name(), 
							true);
			Integer newZendingId = repo.saveAndFlush(newZending).getZendingId();
			
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
					+ " spoed {}."
					,
					zending.getZendingId(),
					zending.getTypeZending(), zending.getAfzender().getNaam(), zending.getAfzender().getStraat(),zending.getAfzender().getPostcode(), 
					zending.getOntvanger().getNaam(), zending.getOntvanger().getStraat(), zending.getOntvanger().getPostcode(), 
					zending.isOphalenBijKlantThuis(),
					zending.getHuidigeLocatie().getNaam(), zending.getHuidigeLocatie().getStraat(), zending.getHuidigeLocatie().getPostcode(), 
					zending.getStatus(),
					zending.isSpoed());
		}
	}
	
	@Bean
	CommandLineRunner testZendingRepository(ZendingRepository repo) {
		return (args) -> {
			log.info("$Testing ZendingRepository.");
			
			log.info(">Find one Zending by id {} from database.", 0);
			Zending zendingById = repo.findOne(0);
			logZendingen(Collections.unmodifiableList(Arrays.asList(zendingById)));
			
			log.info(">Save new Zending to database.");
		
			Zending newZending = new Zending(16,"PAKKET",
			new Adres("Spar GSP", "8800", "Denenstraat 85", "Gent", "Belgie"),
					new Adres("Karel Veke", "7000", "Koeienstraat 10", "Merelen", "Belgie"),
					new Adres("Nick Heldens", "3330", "Paardenstraat 47", "Eergemstraat 80", "Belgie"),			
					false, 
					true);
			
			Integer newZendingId = repo.save(newZending);
			
			
			log.info(">Find all Zendingen with status OP_TE_HALEN.");
			List<Zending> zendingen = repo.findAllOpTeHalen();
			logZendingen(zendingen);
			
			log.info(">Find all Zendingen with status AANGEMAAKT.");
			List<Zending> zendingenOpTeHalen = repo.findAllAangemaakt();
			logZendingen(zendingenOpTeHalen);
			
			log.info(">Find all Zendingen with status OPGEHAALD.");
			List<Zending> zendingenOpgehaald = repo.findAllOpgehaald();
			logZendingen(zendingenOpgehaald);
			
			log.info(">Find all Zendingen with status AFGELEVERD.");
			List<Zending> zendingenAfgeleverd = repo.findAllAfgeleverd();
			logZendingen(zendingenAfgeleverd);
		};
	}
	
	private static void logResponse(Response response) {
		log.info("-response status[{}] message[{}]", response.status, response.message);
	}

	@Bean
	CommandLineRunner testZendingService(ZendingService service) {
		return (args) -> {
			log.info("$Testing ZendingService.");
			Zending newZending = new Zending(15,"PAKKET",
					new Adres("Karel Veke", "7000", "Koeienstraat 10", "Merelen", "Belgie"),
					new Adres("Nick Heldens", "3330", "Paardenstraat 47", "Male", "Belgie"),			
					true, 
					true);
			
			log.info(">Maak nieuwe zending");
			Response response = service.maakNieuweZending(newZending);
			logResponse(response);
			
			log.info(">Bevestig afhalen zending");
			response = service.bevestigAfhalen(newZending.getZendingId());
			logResponse(response);
			
			log.info(">Bevestig aankomst nieuwe zending");
		
			response = service.bevestigAankomstNieuweZending(0, new Adres("Spar GSP", "8800", "Denenstraat 85", "Gent", "Belgie"));
			logResponse(response);
			
			
			log.info(">Bevestig aankomst nieuwe zending 2");
			response = service.bevestigAankomstNieuweZending(1, new Adres("Spar Geluwe", "8100", "Duinenweg 2", "Geluwe", "Belgie"));
			logResponse(response);
			
			log.info(">Bevestig aankomst nieuwe zending 3");
			response = service.bevestigAankomstNieuweZending(2, new Adres("Proxy Delhaize Zulte", "7800", "Meensestraat 125", "Zulte", "Belgie"));
			logResponse(response);
			
			
			log.info(">bevestigAfhalen zending voor id {}", newZending.getZendingId());
			response = service.bevestigAfhalen(newZending.getZendingId());
			
			log.info(">bevestigAfhalen zending voor id {} (fail)",1000);
			response = service.bevestigAfhalen(1000);
			logResponse(response);
		};
	}
	
	@Bean
	CommandLineRunner testEventHandler(EventHandler handler) {
		return (args) -> {
			log.info("$Testing EventHandler.");
			
			BevestigOphalenZendingEvent event = maakBevestigOphalenZendingEvent(0);
			log.info(">Handle BevestigOphalenZendingEvent.");
			handler.handleBevestigOphalenZending(event);
			
			
			BevestigAfleverenZendingEvent afleverenEvent = maakBevestigAfleverenZendingEvent(0,	"Piet klaasen","9000","grieksetraat 20","Gent","Belgie");
			log.info(">Handle BevestigAfleverenZendingEvent.");
			handler.handleBevestigAfleverenZending(afleverenEvent);
		};
	}

	@Bean
	CommandLineRunner testZendingManagementController() {
		return (args) -> {
			try {
				log.info("$Testing ZendingManagementController");
				log.info(">Bevestig aankomst nieuwe zending via Rest Controller.");
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder()
					      .uri(URI.create("http://localhost:2226/api/zendingen/0"))
					      .timeout(Duration.ofMinutes(1))
					      .header("Content-Type", "application/json")
					      .POST(BodyPublishers.ofString(getBody1()))
					      .build();
				HttpResponse<String> response =
				          client.send(request, BodyHandlers.ofString());
				log.info("- response: {}", response.body());
			}
			catch(RuntimeException e) {
				log.info("Failed");
			}
		};
	}
	
	private static BevestigOphalenZendingEvent maakBevestigOphalenZendingEvent(Integer id) {
		BevestigOphalenZendingEvent e = new BevestigOphalenZendingEvent();
		e.setOrderId(id);
		return e;
	}

	private static String getBody1() {
		return "{\n"
				+ "    \"id\": \"0\",\n"
				+ "    \"afhaalpunt\" : {\n"
				+ "        \"naam\" : \"Spar Deinze\",\n"
				+ "        \"postcode\" : \"9005\",\n"
				+ "        \"straat\" : \"Deinzestraat 5\",\n"
				+ "        \"plaats\" : \"Deinze\",\n"
				+ "        \"land\" : \"Belgie\"\n"
				+ "    },\n"
				+ "}";
	}
}
