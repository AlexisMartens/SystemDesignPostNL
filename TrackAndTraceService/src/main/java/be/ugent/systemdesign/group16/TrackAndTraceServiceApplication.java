package be.ugent.systemdesign.group16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import be.ugent.systemdesign.group16.API.messaging.Channels;

@EnableBinding(Channels.class)
@SpringBootApplication
public class TrackAndTraceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackAndTraceServiceApplication.class, args);
	}

}
