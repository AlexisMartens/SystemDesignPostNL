package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.group16.application.event.EventHandler;
import be.ugent.systemdesign.group16.application.event.StuurKoerierEvent;

@Component
public class MessageInputGateway {
	
	@Autowired
	EventHandler eventHandler;
	
	@Autowired
	Channels channels;
	
	@StreamListener(Channels.STUUR_KOERIER)
	public void consumeStuurKoerierEvent(StuurKoerierEvent event) {
		eventHandler.handleStuurKoerier(event);
	}
	
}