package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
public interface Channels {

	static final String NIEUW_SORTEERITEM_EVENT = "nieuw_sorteeritem_event";
	static final String KLAAR_VOOR_KOERIER_EVENT = "klaar_voor_koerier_event";
	static final String ZENDING_EVENT = "zending_event";
	//TODO: output bevestigOphalenZending
	//TODO: output bevestigAfleverenZending
	
	@Output(NIEUW_SORTEERITEM_EVENT)
	MessageChannel NieuwSorteerItemEvent();
	
	@Output(KLAAR_VOOR_KOERIER_EVENT)
	MessageChannel KlaarVoorKoerierEvent();

	@Input(ZENDING_EVENT)
	SubscribableChannel ZendingEvent();
}
