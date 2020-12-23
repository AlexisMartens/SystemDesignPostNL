package be.ugent.systemdesign.group16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableAsync;

import be.ugent.systemdesign.group16.API.messaging.Channels;

@SpringBootApplication
@EnableAsync
@EnableBinding(Channels.class)
public class MagazijnServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagazijnServiceApplication.class, args);
	}


}
