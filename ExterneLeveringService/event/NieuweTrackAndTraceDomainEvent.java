package be.ugent.systemdesign.group16.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NieuweTrackAndTraceDomainEvent {
	
	private String bestellingId;
	private String status;
	
	public NieuweTrackAndTraceDomainEvent(String bestellingId, String status) {
		super();
		this.bestellingId=bestellingId;
		this.status=status;
	}

}
