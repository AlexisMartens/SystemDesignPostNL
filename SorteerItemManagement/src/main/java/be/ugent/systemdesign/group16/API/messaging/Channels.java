package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

	final static String NIEUW_SORTEER_ITEM = "nieuw_sorteer_item";
	final static String STUUR_SORTEERDER = "stuur_sorteerder";
	final static String STUUR_VERVOERDER = "stuur_vervoerder";
	final static String NIEUWE_ZENDING = "nieuwe_zending";
	final static String BEVESTIG_SORTEREN_ITEM = "bevestig_sorteren_item";
	final static String BEVESTIG_VERVOEREN_ITEM = "bevestig_vervoeren_item";
	final static String UPDATE_TRACK_AND_TRACE = "update_track_and_trace_event";
	
	@Input(NIEUW_SORTEER_ITEM)
	SubscribableChannel nieuwSorteerItem();
	
	@Input(BEVESTIG_SORTEREN_ITEM)
	SubscribableChannel bevestigSorteren();
	
	@Input(BEVESTIG_VERVOEREN_ITEM)
	SubscribableChannel bevestigVervoeren();
	
	@Output(NIEUWE_ZENDING)
	MessageChannel nieuweZending();
	
	@Output(STUUR_SORTEERDER)
	MessageChannel stuurSorteerder();
	
	@Output(STUUR_VERVOERDER)
	MessageChannel stuurVervoerder();
	
	@Output(UPDATE_TRACK_AND_TRACE)
	MessageChannel updateTrackAndTrace();
}
