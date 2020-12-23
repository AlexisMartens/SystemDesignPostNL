package be.ugent.systemdesign.group16.domain;

import be.ugent.systemdesign.group16.application.Response;
import be.ugent.systemdesign.group16.domain.seedwork.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class UpdateTrackAndTraceEvent extends DomainEvent {

	private Integer orderId;
	
	private String naam;
	
	private String postcode;
	
	private String straat;
	
	private String plaats;
	
	private String land;
	
	private String status;

	public UpdateTrackAndTraceEvent(Integer orderId, String naam, String postcode, String straat, String plaats,
			String land, String status) {
		this.orderId = orderId;
		this.naam = naam;
		this.postcode = postcode;
		this.straat = straat;
		this.plaats = plaats;
		this.land = land;
		this.status = status;
	}
	
	
	}


