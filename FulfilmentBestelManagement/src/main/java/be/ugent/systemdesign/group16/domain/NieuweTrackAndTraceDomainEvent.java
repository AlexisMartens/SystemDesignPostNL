package be.ugent.systemdesign.group16.domain;

import be.ugent.systemdesign.group16.domain.seedwork.DomainEvent;
import lombok.Getter;

@Getter
public class NieuweTrackAndTraceDomainEvent extends DomainEvent {
	
	private String bestellingId;
	private String status;
	
	public NieuweTrackAndTraceDomainEvent(String bestellingId, String status) {
		super();
		this.bestellingId=bestellingId;
		this.status=status;
	}

}
