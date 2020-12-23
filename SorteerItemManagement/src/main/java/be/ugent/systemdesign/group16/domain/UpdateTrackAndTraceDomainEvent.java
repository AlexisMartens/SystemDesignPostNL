package be.ugent.systemdesign.group16.domain;

import be.ugent.systemdesign.group16.domain.seedwork.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateTrackAndTraceDomainEvent extends DomainEvent{
	
	private Integer bestellingId;
	
	private String naam;
	private String postcode;
	private String straat;
	private String plaats;
	private String land;
	
	private String status;
	
	public UpdateTrackAndTraceDomainEvent(SorteerItem i) {
		super();
		this.bestellingId=i.getTrackId();
		this.naam=i.getHuidigeLocatie().getNaam();
		this.postcode=i.getHuidigeLocatie().getPostcode();
		this.straat=i.getHuidigeLocatie().getStraat();
		this.plaats=i.getHuidigeLocatie().getPlaats();
		this.land=i.getHuidigeLocatie().getLand();
		this.status=i.getStatus().name();
	}
}
