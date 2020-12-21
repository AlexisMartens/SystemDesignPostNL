package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
public interface Channels {

	static final String NIEUW_SORTEERITEM_EVENT = "nieuw_sorteeritem_event";
	static final String KLAAR_VOOR_KOERIER_EVENT = "klaar_voor_koerier_event";
	static final String ZENDING_EVENT = "zending_event";
	static final String ZENDING_EVENT_SORTEERITEM_MGMT = "zending_event_sorteeritem_mgmt";
	static final String OPHALEN_ZENDING_EVENT = "ophalen_zending_event";
	static final String AFLEVEREN_ZENDING_EVENT = "afleveren_zending_event";
	
	@Output(NIEUW_SORTEERITEM_EVENT)
	MessageChannel NieuwSorteerItemEvent();
	
	@Output(KLAAR_VOOR_KOERIER_EVENT)
	MessageChannel KlaarVoorKoerierEvent();

	@Input(ZENDING_EVENT)
	SubscribableChannel ZendingEvent();
	
	//TODO: namen onderstaande checken
	@Input(ZENDING_EVENT_SORTEERITEM_MGMT)
	SubscribableChannel ZendingSorteerItemMgmtEvent();
	
	@Input(OPHALEN_ZENDING_EVENT)
	SubscribableChannel OphalenZendingEvent();
	
	@Input(AFLEVEREN_ZENDING_EVENT)
	SubscribableChannel AfleverenZendingEvent();
}
