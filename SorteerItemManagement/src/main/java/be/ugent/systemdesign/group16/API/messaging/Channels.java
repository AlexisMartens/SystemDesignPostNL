package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	final static String NIEUWE_ZENDING = "";
	final static String NIEUW_SORTEER_ITEM = "";
	final static String STUUR_SORTEERDER = "";
	final static String STUUR_VERVOERDER = "";
	final static String BEVESTIG_SORTEREN = "";
	final static String BEVESTIG_VERVOEREN = "";
	
	@Input(NIEUW_SORTEER_ITEM)
	SubscribableChannel nieuwSorteerItem();
	
	@Input(BEVESTIG_SORTEREN)
	SubscribableChannel bevestigSorteren();
	
	@Input(BEVESTIG_VERVOEREN)
	SubscribableChannel bevestigVervoeren();
	
	@Output(NIEUWE_ZENDING)
	MessageChannel nieuweZending();
	
	@Output(STUUR_SORTEERDER)
	MessageChannel stuurSorteerder();
	
	@Output(STUUR_VERVOERDER)
	MessageChannel stuurVervoerder();
}
