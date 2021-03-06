package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import be.ugent.systemdesign.group16.application.event.EventDispatcher;
import be.ugent.systemdesign.group16.domain.NieuweZendingDomainEvent;
import be.ugent.systemdesign.group16.domain.StuurSorteerderDomainEvent;
import be.ugent.systemdesign.group16.domain.StuurVervoerderDomainEvent;
import be.ugent.systemdesign.group16.domain.UpdateTrackAndTraceDomainEvent;

@MessagingGateway
public interface MessageOutputGateway extends EventDispatcher{
	
	@Gateway(requestChannel = Channels.NIEUWE_ZENDING)
	void publishNieuweZendingDomainEvent(NieuweZendingDomainEvent e);
	
	@Gateway(requestChannel = Channels.STUUR_SORTEERDER)
	void publishStuurSorteerderDomainEvent(StuurSorteerderDomainEvent e);
	
	@Gateway(requestChannel = Channels.STUUR_VERVOERDER)
	void publishStuurVervoerderDomainEvent(StuurVervoerderDomainEvent e);

	@Gateway(requestChannel = Channels.UPDATE_TRACK_AND_TRACE)
	void publishUpdateTrackAndTraceDomainEvent(UpdateTrackAndTraceDomainEvent e);
}