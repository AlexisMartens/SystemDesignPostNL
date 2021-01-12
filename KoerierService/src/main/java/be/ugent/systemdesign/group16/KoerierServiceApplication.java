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
import be.ugent.systemdesign.group16.application.KoerierService;
import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.application.event.EventHandler;
import be.ugent.systemdesign.group16.application.event.StuurKoerierDomainEvent;
import be.ugent.systemdesign.group16.domain.BevestigAfleverenZendingEvent;
import be.ugent.systemdesign.group16.domain.Koerier;
import be.ugent.systemdesign.group16.domain.KoerierRepository;
import be.ugent.systemdesign.group16.infrastructure.KoerierDataModel;
import be.ugent.systemdesign.group16.infrastructure.KoerierDataModelJpaRepository;
import be.ugent.systemdesign.group16.infrastructure.OrderDataModel;
import be.ugent.systemdesign.group16.infrastructure.OrderDataModelJpaRepository;

@SpringBootApplication
@EnableAsync
@EnableBinding(Channels.class)
public class KoerierServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(KoerierServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KoerierServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner populateDatabase(KoerierDataModelJpaRepository kRepo, OrderDataModelJpaRepository oRepo) {
		return (args) -> {
			log.info("Populating databse");
			KoerierDataModel k1 = new KoerierDataModel(1, "Eric", "9900", 150);
			KoerierDataModel k2 = new KoerierDataModel(2, "Chris", "9000", 80);
			KoerierDataModel k3 = new KoerierDataModel(3, "Martin", "9000", 100);
			KoerierDataModel k4 = new KoerierDataModel(4, "John", "4000", 90);
			List<KoerierDataModel> koeriers = Arrays.asList(k1, k2, k3, k4);
			koeriers.forEach(koerier -> kRepo.save(koerier));
			kRepo.flush();
			List<KoerierDataModel> foundKoeriers = kRepo.findAll();
			logKoerierDataModels(foundKoeriers);

			List<OrderDataModel> orders = Arrays.asList(
					new OrderDataModel(1, k1, "Jan klaasen", "9000", "griekstraat 5", "Gent", "Belgie", "Geert Klaasen",
							"9100", "klopperstraat 5", "Sint-Niklaas", "Belgie", LocalDate.now(), false, false,
							"OP_TE_HALEN"),
					new OrderDataModel(2, k2, "Jan klaasen", "9000", "griekstraat 5", "Gent", "Belgie", "Geert Klaasen",
							"9100", "klopperstraat 5", "Sint-Niklaas", "Belgie", LocalDate.now(), false, false,
							"OP_TE_HALEN"),
					new OrderDataModel(3, k2, "Jan klaasen", "9000", "griekstraat 5", "Gent", "Belgie", "Geert Klaasen",
							"9100", "klopperstraat 5", "Sint-Niklaas", "Belgie", LocalDate.now(), false, false,
							"OP_TE_HALEN"));
			orders.forEach(order -> oRepo.save(order));
			oRepo.flush();
			List<OrderDataModel> foundOrders = oRepo.findAll();
			logOrderDataModels(foundOrders);
		};
	}

	private static void logKoerierDataModels(List<KoerierDataModel> koeriers) {
		log.info("-Number of koeriersDM found: {}", koeriers.size());
		for (KoerierDataModel koerier : koeriers) {
			log.info(
					"--koerierId {}       ,  naam {}         , postcodeRonde {}          , vervoercapaciteit {}           ",
					koerier.getKoerierId(), koerier.getNaam(), koerier.getPostcodeRonde(),
					koerier.getVervoercapaciteit());
		}
	}

	@Bean
	CommandLineRunner testKoerierDataModelJpaRepository(KoerierDataModelJpaRepository repo) {
		return (args) -> {
			log.info("$Testing KoerierDataModelJPARepository.");

			log.info(">Find all Koeriers from database.");
			List<KoerierDataModel> koeriersAll = repo.findAll();
			logKoerierDataModels(koeriersAll);

			log.info(">Find all koeriers by postcodeRonde {} from database.", 9000);
			List<KoerierDataModel> koerierByPostcodeRonde = repo.findByPostcodeRonde("9000");
			logKoerierDataModels(koerierByPostcodeRonde);

			log.info(">Save new Koerier to database.");
			KoerierDataModel newKoerier = new KoerierDataModel(10, "Bert Jansens", "9900", 50);
			repo.saveAndFlush(newKoerier);
			Integer newKoerierId = newKoerier.getKoerierId();

			log.info(">Find Koerier by id {} from database.", newKoerierId);
			Optional<KoerierDataModel> koerierById = repo.findById(newKoerierId);
			koerierById.ifPresentOrElse((value) -> {
				logKoerierDataModels(Collections.unmodifiableList(Arrays.asList(value)));
			}, () -> {
				logKoerierDataModels(Collections.emptyList());
			});

			log.info(">Delete Bestelling by id {} from database.", newKoerierId);
			repo.deleteById(newKoerierId);
		};
	}

	private static void logKoeriers(List<Koerier> koeriers) {
		log.info("-Number of koeriers found: {}", koeriers.size());
		for (Koerier koerier : koeriers) {
			log.info(
					"--koerierId {}       ,  naam {}         , postcodeRonde {}          , vervoercapaciteit {}          ",
					koerier.getKoerierId(), koerier.getNaam(), koerier.getPostcodeRonde(),
					koerier.getVervoercapaciteit());
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
			Koerier newKoerier = new Koerier(newKoerierId, "Bert Jansens", "9900", 50);
			repo.save(newKoerier);

			log.info(">Find all koeriers that have postcodeRonde {}", "9000");
			List<Koerier> koeriers = repo.findByPostcodeRonde("9000");
			logKoeriers(koeriers);
		};
	}

	private static void logOrderDataModels(List<OrderDataModel> orders) {
		log.info("-Number of orders found: {}", orders.size());
		for (OrderDataModel order : orders) {
			log.info("--orderId {}       , naamAfzender {}", order.getOrderId(), order.getNaamAfzender());
		}
	}

	@Bean
	CommandLineRunner testOrderDataModelJpaRepository(OrderDataModelJpaRepository Orepo,
			KoerierDataModelJpaRepository Krepo) {
		return (args) -> {
			log.info("$Testing OrderDataModelJpaRepository.");

			log.info(">Find all Orders from database.");
			List<OrderDataModel> ordersAll = Orepo.findAll();
			logOrderDataModels(ordersAll);

			KoerierDataModel koerierDM = Krepo.findById(1).orElse(null);
			;
			Integer numberOfOrders = Orepo.countByKoerierDataModel(koerierDM);
			log.info("-Number of orders found for Koerier 1: {}", numberOfOrders);

			log.info(">Save new Order to database.");
			OrderDataModel newOrder = new OrderDataModel(11, koerierDM, "Jan klaasen", "9000", "griekstraat 5", "Gent",
					"Belgie", "Geert Klaasen", "9100", "klopperstraat 5", "Sint-Niklaas", "Belgie", LocalDate.now(),
					false, false, "OP_TE_HALEN");
			Orepo.saveAndFlush(newOrder);
			Integer newOrderId = newOrder.getOrderId();

			log.info(">Find Order by id {} from database.", newOrderId);
			Optional<OrderDataModel> orderById = Orepo.findById(newOrderId);
			orderById.ifPresentOrElse((value) -> {
				logOrderDataModels(Collections.unmodifiableList(Arrays.asList(value)));
			}, () -> {
				logOrderDataModels(Collections.emptyList());
			});
			log.info(">Delete Order by id {} from database.", newOrderId);
			Orepo.deleteById(newOrderId);
		};
	}

	private static void logResponse(Response response) {
		log.info("-response status[{}] message[{}]", response.status, response.message);
	}

	@Bean
	CommandLineRunner testInpatientService(KoerierService service, KoerierRepository repo) {
		return (args) -> {
			log.info("$Testing KoerierService.");
			Response response;

			log.info(">stuurKoerier (success).");
			response = service.stuurKoerier(50, "PAKKET", "Jan Vander Broek", "9000", "Hoektraat 150", "Gent", "Belgie",
					"Hans Landeghem", "9900", "Grootstraat 4", "Geverghem", "Belgie", "Piet klaasen", "9000",
					"grieksetraat 20", "Gent", "Belgie", false);
			logResponse(response);

			log.info(">stuurKoerier (fail).");
			response = service.stuurKoerier(51, "PAKKET", "Jan Vander Broek", "0000", "Hoektraat 150", "Gent", "Belgie",
					"Hans Landeghem", "9900", "Grootstraat 4", "Geverghem", "Belgie", "Piet klaasen", "9000",
					"grieksetraat 20", "Gent", "Belgie", false);
			logResponse(response);

			log.info(">bevestigAfleverenBuren (success).");
			response = service.bevestigAfleverenBuren(1);
			logResponse(response);

			log.info(">bevestigAfleveren (success).");
			response = service.bevestigAfleveren(2);
			logResponse(response);

			log.info(">bevestigOphalen (success).");
			response = service.bevestigOphalen(3);
			logResponse(response);
		};
	}

	@Bean
	CommandLineRunner testEventHandler(EventHandler handler) {
		return (args) -> {
			log.info("$Testing EventHandler.");

			StuurKoerierDomainEvent event = maakStuurKoerierDomainEvent(52, "PAKKET", "Jan Vander Broek", "9000",
					"Hoektraat 150", "Gent", "Belgie", "Hans Landeghem", "9900", "Grootstraat 4", "Geverghem", "Belgie",
					"Piet klaasen", "9000", "grieksetraat 20", "Gent", "Belgie", false);
			log.info(">Handle StuurKoerierDomainEvent.");
			handler.handleStuurKoerier(event);
		};
	}

	@Bean
	CommandLineRunner testKoerierRestController() {
		return (args) -> {
			try {
				log.info("$Testing KoerierRestController");
				log.info(">Bevestig ophalen order via Rest Controller.");
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder()
						.uri(URI.create("http://localhost:2229/api/koerier/1/opgehaald")).timeout(Duration.ofMinutes(1))
						.header("Content-Type", "application/json").PUT(BodyPublishers.ofString(getBody1())).build();
				HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
				log.info("- response: {}", response.body());

				log.info(">Bevestig afleveren order via Rest Controller.");
				client = HttpClient.newHttpClient();
				request = HttpRequest.newBuilder().uri(URI.create("http://localhost:2229/api/koerier/1/afgeleverd"))
						.timeout(Duration.ofMinutes(1)).header("Content-Type", "application/json")
						.PUT(BodyPublishers.ofString(getBody2())).build();
				response = client.send(request, BodyHandlers.ofString());
				log.info("- response: {}", response.body());

				log.info(">Bevestig afleveren buren order via Rest Controller.");
				client = HttpClient.newHttpClient();
				request = HttpRequest.newBuilder()
						.uri(URI.create("http://localhost:2229/api/koerier/2/afgeleverdBuren"))
						.timeout(Duration.ofMinutes(1)).header("Content-Type", "application/json")
						.PUT(BodyPublishers.ofString(getBody3())).build();
				response = client.send(request, BodyHandlers.ofString());
				log.info("- response: {}", response.body());
			} catch (RuntimeException e) {
				log.info("Failed");
			}
		};
	}

	private static StuurKoerierDomainEvent maakStuurKoerierDomainEvent(Integer zendingId, String typeZending,
			String naamAfzender, String postcodeAfzender, String straatAfzender, String plaatsAfzender,
			String landAfzender, String naamOntvanger, String postcodeOntvanger, String straatOntvanger,
			String plaatsOntvanger, String landOntvanger, String naamHuidigeLocatie, String postcodeHuidigeLocatie,
			String straatHuidigeLocatie, String plaatsHuidigeLocatie, String landHuidigeLocatie, boolean spoed) {
		StuurKoerierDomainEvent event = new StuurKoerierDomainEvent(zendingId, typeZending, naamAfzender,
				postcodeAfzender, straatAfzender, plaatsAfzender, landAfzender, naamOntvanger, postcodeOntvanger,
				straatOntvanger, plaatsOntvanger, landOntvanger, naamHuidigeLocatie, postcodeHuidigeLocatie,
				straatHuidigeLocatie, plaatsHuidigeLocatie, landHuidigeLocatie, spoed);
		return event;
	}

	private static String getBody1() {
		return "{\n" + "    \"id\": \"1\",\n" + "}";
	}

	private static String getBody2() {
		return "{\n" + "    \"id\": \"1\",\n" + "}";
	}

	private static String getBody3() {
		return "{\n" + "    \"id\": \"2\",\n" + "}";
	}

}
