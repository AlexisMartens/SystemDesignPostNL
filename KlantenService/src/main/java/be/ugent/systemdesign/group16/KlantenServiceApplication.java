package be.ugent.systemdesign.group16;

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
import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.application.command.GetKlantenDataResponse;
import be.ugent.systemdesign.group16.domain.FulfilmentKlant;
import be.ugent.systemdesign.group16.domain.FulfilmentKlantRepository;

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
				response.getNaam(), response.getKlantenId());
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
			GetKlantenDataResponse response2 = service.getKlantenData("1");
			logGetKlantenDataResponse(response2);

			log.info(">Delete FulfilmentKlant voor id {}", newFulfilmentKlant.getKlantId());
			FulfilmentKlant k = repo.findOne(1);
			response = service.stopFulfilmentKlant(k.getKlantId());
			logResponse(response);
		};
	}
	// testen of het intern werkt
	// fulfilment
	// koerier

	// testen of communicatie werkt

	// docker containers maken

}
