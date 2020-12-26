package be.ugent.systemdesign.group16.domain;

import be.ugent.systemdesign.group16.domain.seedwork.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BevestigSorterenItemDomainEvent extends DomainEvent{
	
	private Integer sorteerItemId;
	
	private String naamVolgendeLocatie;
	private String postcodeVolgendeLocatie;
 	private String straatVolgendeLocatie;
 	private String plaatsVolgendeLocatie;
 	private String landVolgendeLocatie;

	private Integer batchId;
	private boolean laatsteLocatie;
	
	public BevestigSorterenItemDomainEvent(Integer sorteerItemId, Adres volgendeLocatie, Integer batchId, boolean laatsteLocatie) {
		super();
		this.sorteerItemId=sorteerItemId;
		this.naamVolgendeLocatie=volgendeLocatie.getNaam();
		this.postcodeVolgendeLocatie=volgendeLocatie.getPostcode();
		this.straatVolgendeLocatie=volgendeLocatie.getStraat();
		this.plaatsVolgendeLocatie=volgendeLocatie.getPlaats();
		this.landVolgendeLocatie=volgendeLocatie.getLand();
		this.batchId=batchId;
		this.laatsteLocatie=laatsteLocatie;
	}
}
