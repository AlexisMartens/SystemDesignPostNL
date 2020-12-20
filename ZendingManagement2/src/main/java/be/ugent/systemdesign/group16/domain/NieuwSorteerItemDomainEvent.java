package be.ugent.systemdesign.group16.domain;

import be.ugent.systemdesign.group16.domain.seedwork.DomainEvent;
import lombok.Getter;
@Getter
public class NieuwSorteerItemDomainEvent extends DomainEvent {
	//TODO: attributen
	private String zendingId;
	private String status;
	//TODO: args constructor
	public NieuwSorteerItemDomainEvent(String _zendingId, String _status) {
		super();
		//TODO: initialiseren attr'n
		this.zendingId = _zendingId;
		this.status = _status;
	}
}
