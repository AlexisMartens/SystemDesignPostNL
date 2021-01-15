package be.ugent.systemdesign.group16;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import be.ugent.systemdesign.group16.API.messaging.Channels;
import be.ugent.systemdesign.group16.API.messaging.MessageOutputGateway;
import be.ugent.systemdesign.group16.application.FulfilmentBestelService;
import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.application.command.GetKlantenDataCommand;
import be.ugent.systemdesign.group16.domain.Bestelling;
import be.ugent.systemdesign.group16.infrastructure.BestellingDataModel;
import be.ugent.systemdesign.group16.infrastructure.BestellingDataModelRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAsync
@EnableBinding(Channels.class)
@SpringBootApplication
public class FulfilmentBestelManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FulfilmentBestelManagementApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(FulfilmentBestelManagementApplication.class);

	@Bean
	public CommandLineRunner populateDatabase(BestellingDataModelRepository repo) {
		return (args) -> {
			log.info("Populating databse");
			List<BestellingDataModel> bestellingen = Arrays.asList(
					new BestellingDataModel(0,"Pakket","Jan klaasen","9000","griekstraat 5","Gent","Belgie","Geert Klaasen","9100","klopperstraat 5","Sint-Niklaas","Belgie",true,LocalDate.now(),"AANGEMAAKT",false),
					new BestellingDataModel(1,"Pakket","Piet klaasen","9000","grieksetraat 20","Gent","Belgie","Guy Klaasen","9100","klopperstraat 3","Sint-Niklaas","Belgie",false,LocalDate.now(),"AANGEMAAKT",false),
					new BestellingDataModel(2,"Pakket","Piet klaasen","9000","grieksetraat 20","Gent","Belgie","Guy Klaasen","9100","klopperstraat 3","Sint-Niklaas","Belgie",false,LocalDate.now(),"AANGEMAAKT",false)
					);
			bestellingen.forEach(bestelling -> repo.save(bestelling));
			repo.flush();
		};
	}
	
	@Value("${spring.cloud.stream.bindings." + Channels.GET_KLANTEN_DATA_RESPONSE + ".destination}")
	String responseDestination;

	private static void logResponse(Response response) {
		log.info("-response status[{}] message[{}]", response.status, response.message);
	}

	@Bean
	CommandLineRunner testFulfilmentBestelService(FulfilmentBestelService service) {
		return (args) -> {
			log.info("$Testing BestelService.");

			log.info(">maak een bestelling");
			Bestelling newbestelling = new Bestelling("Pakket", "Jan Vander Broek", "7000", "kaastraat 150", "Gent",
					"Belgie", "Hans Landeghem", "4564", "geefstraat 4", "Geverghem", "Belgie", false);

			log.info(">plaatsBestelling");
			Response response = service.plaatsBestelling(newbestelling);
			log.info(">logResponse");
			logResponse(response);
		};
	}
	
	@Bean
	CommandLineRunner testgetKlantenDataCommand(MessageOutputGateway outputGateway) {
		return (args) -> {
			outputGateway.sendGetKlantenDataCommand(new GetKlantenDataCommand("2", responseDestination));
		};
	}

	@Bean
	CommandLineRunner testFulfilmentBestelManagementController() {
		return (args) -> {
			try {
				log.info("$Testing FulfilmentBestelManagementController");
				log.info(">Plaats Bestelling bij FulfilmentBestelManagement via Rest Controller.");
				HttpClient client = HttpClient.newHttpClient();
				HttpRequest request = HttpRequest.newBuilder()
						.uri(URI.create("http://localhost:2002/api/fulfilmentbestel/"))
						.timeout(Duration.ofMinutes(1))
						.header("Content-Type", "application/json")
						.POST(BodyPublishers.ofString(getBody()))
						.build();
				HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
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
				+ "    \"bestellingId\": \"1000\",\n"
				+ "    \"typeBestelling\": \"PAKKET\",\n"
				+ "    \"ontvanger\" : {\n"
				+ "        \"naam\" : \"Lodewijk XIV\",\n"
				+ "        \"postcode\" : \"9000\",\n"
				+ "        \"straat\" : \"straat\",\n"
				+ "        \"plaats\" : \"Nevele\",\n"
				+ "        \"land\" : \"Belgie\"\n"
				+ "    },\n"
				+ "    \"afzender\" : {\n"
				+ "        \"naam\" : \"Karel de Grote\",\n"
				+ "        \"postcode\" : \"9000\",\n"
				+ "        \"straat\" : \"straat\",\n"
				+ "        \"plaats\" : \"Nevele\",\n"
				+ "        \"land\" : \"Belgie\"\n"
				+ "    },\n"
				+ "    \"aanmaakDatum\" : \"2021-01-01T00:00:00\",\n"
				+ "    \"status\": \"AANGEMAAKT\",\n"
				+ "    \"spoed\": \"true\"\n"
				+ "}";
	}

}
