package be.ugent.systemdesign.group16.domain.seedwork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AggregateRoot {

	private final List<DomainEvent> events = new ArrayList<>();
	
	public List<DomainEvent> getDomainEvents(){
		return Collections.unmodifiableList(events);
	}
	
	public void clearList() {
		events.clear();
	}
	
	protected void addDomainEvent(DomainEvent event) {
		events.add(event);
	}
}
