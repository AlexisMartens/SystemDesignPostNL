package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	final static String STUUR_SORTEERDER = "stuur_sorteerder";
	final static String BEVESTIG_SORTEREN_ITEM = "bevestig_sorteren_item";

	@Input(STUUR_SORTEERDER)
	SubscribableChannel stuurSorteerder();
	
	@Output(BEVESTIG_SORTEREN_ITEM)
	MessageChannel bevestigSorterenItem();
}
