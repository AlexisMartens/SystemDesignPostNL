package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.systemdesign.group16.domain.NieuweZendingDomainEvent;
import be.ugent.systemdesign.group16.domain.StuurSorteerderDomainEvent;
import be.ugent.systemdesign.group16.domain.StuurVervoerderDomainEvent;

@MessagingGateway
public interface MessageOutputGateway {
	
	@Gateway(requestChannel = Channels.NIEUWE_ZENDING)
	void publishNieuweZendingEvent(NieuweZendingDomainEvent e);
	
	@Gateway(requestChannel = Channels.STUUR_SORTEERDER)
	void publishStuurSorteerderEvent(StuurSorteerderDomainEvent e);
	
	@Gateway(requestChannel = Channels.STUUR_VERVOERDER)
	void publishStuurVervoerderEvent(StuurVervoerderDomainEvent e);

}