package be.ugent.systemdesign.group16.application.event;

import be.ugent.systemdesign.group16.domain.BevestigSorterenItemDomainEvent;

public interface EventDispatcher {
	void publishBevestigSorterenItemDomainEvent(BevestigSorterenItemDomainEvent e);
}
