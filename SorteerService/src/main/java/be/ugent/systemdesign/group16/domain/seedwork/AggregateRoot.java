package be.ugent.systemdesign.group16.domain.seedwork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AggregateRoot {
	
	private final List<DomainEvent> events = new ArrayList<>();
	
	public void addDomainEvent(DomainEvent e) {
		this.events.add(e);
	}
	
	public List<DomainEvent> getEvents(){
		return Collections.unmodifiableList(this.events);
	}
	
	public void clearEvents() {
		this.events.clear();
	}
}
