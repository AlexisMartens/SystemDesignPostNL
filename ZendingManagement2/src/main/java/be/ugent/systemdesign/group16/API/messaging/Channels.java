package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
public interface Channels {

	static final String NIEUW_SORTEER_ITEM = "nieuw_sorteer_item";
	static final String STUUR_KOERIER_EVENT = "stuur_koerier_event";
	static final String ZENDING_EVENT = "zending_event";
	static final String NIEUWE_ZENDING = "nieuwe_zending";
	static final String OPHALEN_ZENDING_EVENT = "ophalen_zending_event";
	static final String AFLEVEREN_ZENDING_EVENT = "afleveren_zending_event";
	
	@Output(NIEUW_SORTEER_ITEM)
	MessageChannel NieuwSorteerItemEvent();
	
	@Output(STUUR_KOERIER_EVENT)
	MessageChannel StuurKoerierEvent();

	@Input(ZENDING_EVENT)
	SubscribableChannel ZendingEvent();
	
	@Input(NIEUWE_ZENDING)
	SubscribableChannel ZendingSorteerItemMgmtEvent();
	
	@Input(OPHALEN_ZENDING_EVENT)
	SubscribableChannel OphalenZendingEvent();
	
	@Input(AFLEVEREN_ZENDING_EVENT)
	SubscribableChannel AfleverenZendingEvent();
}
