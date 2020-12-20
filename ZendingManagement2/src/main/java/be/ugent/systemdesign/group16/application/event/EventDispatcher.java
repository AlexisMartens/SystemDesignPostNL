package be.ugent.systemdesign.group16.application.event;

import be.ugent.systemdesign.group16.domain.KlaarVoorKoerierDomainEvent;
import be.ugent.systemdesign.group16.domain.NieuwSorteerItemDomainEvent;

public interface EventDispatcher {

	void publishKlaarVoorKoerierEvent(KlaarVoorKoerierDomainEvent event);
	
	void publishNieuwSorteerItemEvent(NieuwSorteerItemDomainEvent event);
}
