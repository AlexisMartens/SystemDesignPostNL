package be.ugent.systemdesign.group16;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import be.ugent.systemdesign.group16.API.messaging.Channels;
import be.ugent.systemdesign.group16.application.FulfilmentKlantService;
import be.ugent.systemdesign.group16.application.FulfilmentKlantServiceImpl;
import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.application.command.GetKlantenDataResponse;
import be.ugent.systemdesign.group16.domain.FulfilmentKlant;
import be.ugent.systemdesign.group16.domain.FulfilmentKlantRepository;
import be.ugent.systemdesign.group16.infrastructure.FulfilmentKlantDataModel;
import be.ugent.systemdesign.group16.infrastructure.FulfilmentKlantDataModelRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAsync
@EnableBinding(Channels.class)
@SpringBootApplication
public class KlantenServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(KlantenServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KlantenServiceApplication.class, args);
	}

	private static void logResponse(Response response) {
		log.info("-response status[{}] message[{}]", response.status, response.message);
	}

	private static void logGetKlantenDataResponse(GetKlantenDataResponse response) {
		log.info("-response status[{}] message[{}] naam[{}] id[{}]", response.status, response.message,
				response.getNaam(), response.getKlantId());
	}
	
	@Bean
	public CommandLineRunner populateDatabase(FulfilmentKlantDataModelRepository repo) {
		return (args) -> {
			log.info("Populating databse");
			List<FulfilmentKlantDataModel> klanten = Arrays.asList(
					new FulfilmentKlantDataModel(0, "Jan"),
					new FulfilmentKlantDataModel(1, "Piet"),
					new FulfilmentKlantDataModel(2, "Joris")
					);
			klanten.forEach(klant -> repo.save(klant));
			repo.flush();
		};
	}

	@Bean
	CommandLineRunner testFulfilmentKlantService(FulfilmentKlantService service, FulfilmentKlantRepository repo) {
		return (args) -> {
			log.info("$Testing BestelService.");

			log.info(">Maak nieuwe FulfilmentKlant");
			FulfilmentKlant newFulfilmentKlant = new FulfilmentKlant(5, "Mediamarkt");
			Response response = service.maakFulfilmentKlant(newFulfilmentKlant);
			logResponse(response);

			log.info(">Vraag FulfilmentKlantData");
			GetKlantenDataResponse response2 = service.getKlantenData(1);
			logGetKlantenDataResponse(response2);

			log.info(">Delete FulfilmentKlant voor id {}", newFulfilmentKlant.getKlantId());
			FulfilmentKlant k = repo.findOne(0);
			response = service.stopFulfilmentKlant(k.getKlantId());
			logResponse(response);
		};
	}


	@Bean
	CommandLineRunner testFulfilmentKlantManagementController(FulfilmentKlantServiceImpl service) {
		return (args) -> {
			try {
				log.info("$Testing FulfilmentKlantManagementController");
				log.info(">maakFulfilment klant bij KlantenService via Rest Controller.");
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder()
						.uri(URI.create("http://localhost:2003/api/fulfilmentklant/"))
						.timeout(Duration.ofMinutes(1))
						.header("Content-Type", "application/json")
						.POST(BodyPublishers.ofString(getBody()))
						.build();
				HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
				log.info("- response: {}", response.body());

				log.info(">stopFulfilment klant bij KlantenService via Rest Controller.");
				client = HttpClient.newHttpClient();
				request = HttpRequest.newBuilder()
						.uri(URI.create("http://localhost:2003/api/fulfilmentklant/555"))
						.timeout(Duration.ofMinutes(1))
						.DELETE()
						.build();
				response = client.send(request, BodyHandlers.ofString());
				log.info("- response: {}", response.body());
				
				
			} catch (RuntimeException e) {
				log.info("Failed");
			}catch (Exception e) {
				log.info("Failed");
			}
		};
	}

	private static String getBody() {
		return "{\n"
				+ "    \"klantId\": \"555\",\n"
				+ "    \"naam\": \"Pieter Peeters\"\n"
				+ "}";
	}

}
