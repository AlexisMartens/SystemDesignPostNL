package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.group16.application.event.EventHandler;
import be.ugent.systemdesign.group16.application.event.StuurSorteerderEvent;

@Component
public class MessageInputGateway {
	
	@Autowired
	EventHandler eventHandler;

	@StreamListener(Channels.STUUR_SORTEERDER)
	public void consumeStuurSorteerderEvent(StuurSorteerderEvent e) {
		eventHandler.handleStuurVervoerderEvent(e);
	}
}
