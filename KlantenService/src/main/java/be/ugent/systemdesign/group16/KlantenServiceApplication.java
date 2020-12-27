package be.ugent.systemdesign.group16;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableAsync;

import be.ugent.systemdesign.group16.API.messaging.Channels;

@EnableAsync
@EnableBinding(Channels.class)
@SpringBootApplication
public class KlantenServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KlantenServiceApplication.class, args);
		
		//testen of het intern werkt x 3
		
		//testen of communicatie werkt
		
		//docker containers maken
	}

}
