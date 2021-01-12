package be.ugent.systemdesign.group16.application.event;

import be.ugent.systemdesign.group16.domain.StuurKoerierDomainEvent;
import be.ugent.systemdesign.group16.domain.NieuwSorteerItemDomainEvent;

public interface EventDispatcher {

	void publishStuurKoerierEvent(StuurKoerierDomainEvent event);
	
	void publishNieuwSorteerItemEvent(NieuwSorteerItemDomainEvent event);
}
