package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
	
	final static String STUUR_VERVOERDER = "stuur_vervoerder";
	final static String BEVESTIG_VERVOEREN_ITEM = "bevestig_vervoeren_item";

	@Input(STUUR_VERVOERDER)
	SubscribableChannel stuurVervoerder();
	
	@Output(BEVESTIG_VERVOEREN_ITEM)
	MessageChannel bevestigVervoerenItem();
}
