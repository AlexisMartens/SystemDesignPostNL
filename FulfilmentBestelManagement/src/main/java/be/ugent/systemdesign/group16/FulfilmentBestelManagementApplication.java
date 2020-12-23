package be.ugent.systemdesign.group16;

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
@EnableAsync
@EnableBinding(Channels.class)
@SpringBootApplication
public class FulfilmentBestelManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FulfilmentBestelManagementApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(FulfilmentBestelManagementApplication.class);
	
	@Value("${spring.cloud.stream.bindings." + Channels.GET_KLANTEN_DATA_EVENT + ".destination}")
	String responseDestination;

	
	@Bean
	CommandLineRunner testAssignRoomCommand(MessageOutputGateway outputGateway) {
		return (args) -> {
			outputGateway.sendGetKlantenDataCommand(new GetKlantenDataCommand("2", responseDestination));
		};
	}
}
