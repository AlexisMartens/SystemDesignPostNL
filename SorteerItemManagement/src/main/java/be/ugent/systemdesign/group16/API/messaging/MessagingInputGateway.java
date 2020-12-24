package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.group16.application.event.BevestigSorterenItemEvent;
import be.ugent.systemdesign.group16.application.event.BevestigVervoerenItemEvent;
import be.ugent.systemdesign.group16.application.event.EventHandler;
import be.ugent.systemdesign.group16.application.event.NieuwSorteerItemEvent;

@Component
public class MessagingInputGateway {
	
	@Autowired
	EventHandler eventHandler;

	@StreamListener(Channels.NIEUW_SORTEER_ITEM)
	public void consumeNieuwSorteerItemEvent(NieuwSorteerItemEvent e) {
		eventHandler.handleNieuwSorteerItemEvent(e);
	}
	
	@StreamListener(Channels.BEVESTIG_SORTEREN_ITEM)
	public void consumeBevestigSorterenEvent(BevestigSorterenItemEvent e) {
		eventHandler.handleBevestigSorterenEvent(e);
	}
	
	@StreamListener(Channels.BEVESTIG_VERVOEREN_ITEM)
	public void consumeBevestigVervoerenEvent(BevestigVervoerenItemEvent e) {
		eventHandler.handleBevestigVervoerenEvent(e);
	}
}
