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
import org.springframework.scheduling.annotation.EnableAsync;

import be.ugent.systemdesign.group16.API.messaging.Channels;
import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.application.event.EventHandler;
import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Pakket;
import be.ugent.systemdesign.group16.domain.PakketRepository;
import be.ugent.systemdesign.group16.application.MagazijnService;

import be.ugent.systemdesign.group16.domain.PakketStatus;
import be.ugent.systemdesign.group16.domain.UpdateTrackAndTraceDomainEvent;
import be.ugent.systemdesign.group16.infrastructure.PakketDataModel;
import be.ugent.systemdesign.group16.infrastructure.PakketDataModelRepository;
import be.ugent.systemdesign.group16.application.event.PacketDomainEvent;

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
	
	private static void logPakketten(List<Pakket> pakketten) {
		log.info("-Number of pakketten found: {}", pakketten.size());
		for(Pakket pakket : pakketten) {
			log.info("--pakketId {};"
					+ " naamAfzender {}, straatAfzender {}, postcodeAfzender {},"
					+ " naamOntvanger {}, straatOntvanger {}, postcodeOntvanger {}, "
					+ " naamHuidigeLocatie {}, straatHuidigeLocatie {}, postcodeHuidigeLocatie {}, "
					+ " soort {},"
					+ " ophalenBijKlant {},"
					+ " spoed {},"
					+ " status {}."
					,
					pakket.getPakketId(),
					pakket.getAfzender().getNaam(), pakket.getAfzender().getStraat(),pakket.getAfzender().getPostcode(), 
					pakket.getOntvanger().getNaam(), pakket.getOntvanger().getStraat(), pakket.getOntvanger().getPostcode(), 
					pakket.getHuidigeLocatie().getNaam(), pakket.getHuidigeLocatie().getStraat(), pakket.getHuidigeLocatie().getPostcode(), 
					pakket.getSoort(),
					pakket.isOphalenBijKlant(),
					pakket.isSpoed(),
					pakket.getStatus());
		}
	}
	
	@Bean
	CommandLineRunner testPakketRepository(PakketRepository repo) {
		return (args) -> {
			log.info("$Testing PakketRepository.");
			
			log.info(">Find one Pakket by id {} from database.", 0);
			Pakket pakketById = repo.findOne(0);
			logPakketten(Collections.unmodifiableList(Arrays.asList(pakketById)));
			
			log.info(">Save new Pakket to database.");
		
			Pakket newPakket = new Pakket(16,
					"Karel Veke", "7000", "Koeienstraat 10", "Merelen", "Belgie",
					"Nick Heldens", "3330", "Paardenstraat 47", "Eergemstraat 80", "Belgie",			
					"PAKKET",
					true,
					false);
			
			Integer newPakketId = repo.save(newPakket);
			
			log.info(">Find all Pakketten with status AANGEMAAKT.");
			List<Pakket> pakketten = repo.findAllAangemaakt();
			logPakketten(pakketten);
		};
	}

	private static void logResponse(Response response) {
		log.info("-response status[{}] message[{}]", response.status, response.message);
	}

	@Bean
	CommandLineRunner testMagazijnService(MagazijnService service) {
		return (args) -> {
			log.info("$Testing MagazijnService.");
			Pakket newPakket = new Pakket(16,
					"Karel Veke", "7000", "Koeienstraat 10", "Merelen", "Belgie",
					"Nick Heldens", "3330", "Paardenstraat 47", "Eergemstraat 80", "Belgie",			
					"PAKKET",
					true,
					false);
			
			log.info(">Maak nieuw Pakket");
			Response response = service.maakPakket(newPakket);
			logResponse(response);
			
			log.info(">Bevestig inpakken Pakket");
			response = service.BevestigInpakken(newPakket.getPakketId());
			logResponse(response);
			//TODO: updaten track en trace testen
			/*log.info(">Bevestig inpakken Pakket");
			response = service.UpdateTrackAndTrace(new UpdateTrackAndTraceDomainEvent(newPakket.getPakketId(), naam, postcode, straat, plaats, land, newPakket.getStatus()))
			logResponse(response);*/
	
		};
	}
	
	
	@Bean
	CommandLineRunner testEventHandler(EventHandler handler) {
		return (args) -> {
			log.info("$Testing EventHandler.");
			PacketDomainEvent event = maakPacketEvent(14, LocalDate.now(),
					"Karel Veke", "7000", "Koeienstraat 10", "Merelen", "Belgie",
					"Nick Heldens", "3330", "Paardenstraat 47", "Eergemstraat 80", "Belgie",
					true, "PAKKET", false);
			handler.handleNieuwPakket(event);			
		};
	}
	 
		
	private static PacketDomainEvent maakPacketEvent(Integer id, LocalDate aanmaakDatum, 
			String naamAfzender, String postcodeAfzender, String straatAfzender, String plaatsAfzender, String landAfzender,
			String naamOntvanger, String postcodeOntvanger, String straatOntvanger, String plaatsOntvanger, String landOntvanger,
			boolean ophalen, String soort, boolean spoed) {
		
		PacketDomainEvent e = new PacketDomainEvent();
		
		e.setBestellingId(id);
		
		e.setNaamOntvanger(naamOntvanger);
		e.setPostcodeOntvanger(postcodeOntvanger);
		e.setStraatOntvanger(straatOntvanger);
		e.setPlaatsOntvanger(plaatsOntvanger);
		e.setLandOntvanger(landOntvanger);
		
		e.setAanmaakDatum(aanmaakDatum);
		
		e.setNaamAfzender(naamAfzender);
		e.setPostcodeAfzender(postcodeAfzender);
		e.setStraatAfzender(straatAfzender);
		e.setPlaatsAfzender(plaatsAfzender);
		e.setLandAfzender(landAfzender);
		
		e.setOphalen(ophalen);
		
		e.setTypeBestelling(soort);
		
		e.setSpoed(spoed);		
		return e;
	}
	
	@Bean
	CommandLineRunner testMagazijnServiceController() {
		return (args) -> {
			try {
				log.info("$Testing MagazijnServiceController");
				
				log.info(">Bevestig inpakken pakket via Rest Controller.");
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder()
					      .uri(URI.create("http://localhost:2225/api/MagazijnService/0/bevestigInpakken"))
					      .timeout(Duration.ofMinutes(1))
					      .header("Content-Type", "application/json")
					      .POST(BodyPublishers.ofString(getBody()))
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
	
	private static String getBody() {
		return "{\n"
				+ "    \"id\": \"0\",\n"
				+ "}";
	}
}
