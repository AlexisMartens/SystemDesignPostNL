package be.ugent.systemdesign.group16.domain;

import be.ugent.systemdesign.group16.domain.seedwork.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StuurVervoerderDomainEvent extends DomainEvent{
	
	private Integer sorteerItemId;
	
	private String naamHuidigeLocatie;
	private String postcodeHuidigeLocatie;
 	private String straatHuidigeLocatie;
 	private String plaatsHuidigeLocatie;
 	private String landHuidigeLocatie;
 	
 	private String naamVolgendeLocatie;
	private String postcodeVolgendeLocatie;
 	private String straatVolgendeLocatie;
 	private String plaatsVolgendeLocatie;
 	private String landVolgendeLocatie;
 	
	private Integer batchId;
	private boolean spoed;
	
	public StuurVervoerderDomainEvent(Integer sorteerItemId, Adres huidigeLocatie, Adres volgendeLocatie, Integer batchId, boolean spoed) {
		super();
		
		this.sorteerItemId=sorteerItemId;
		
		this.naamHuidigeLocatie=huidigeLocatie.getNaam();
		this.postcodeHuidigeLocatie=huidigeLocatie.getPostcode();
		this.straatHuidigeLocatie=huidigeLocatie.getStraat();
		this.plaatsHuidigeLocatie=huidigeLocatie.getPlaats();
		this.landHuidigeLocatie=huidigeLocatie.getLand();
		
		this.naamVolgendeLocatie=volgendeLocatie.getNaam();
		this.postcodeVolgendeLocatie=volgendeLocatie.getPostcode();
		this.straatVolgendeLocatie=volgendeLocatie.getStraat();
		this.plaatsVolgendeLocatie=volgendeLocatie.getPlaats();
		this.landVolgendeLocatie=volgendeLocatie.getLand();
		
		this.batchId=batchId;
		this.spoed=spoed;
	}
}
