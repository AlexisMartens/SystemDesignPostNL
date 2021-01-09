package be.ugent.systemdesign.group16.domain;

import be.ugent.systemdesign.group16.domain.seedwork.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NieuweTrackAndTraceDomainEvent extends DomainEvent {
	
	private String bestellingId;
	private String status;

}
