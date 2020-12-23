package be.ugent.systemdesign.group16.domain;

import be.ugent.systemdesign.group16.domain.seedwork.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StuurSorteerderDomainEvent extends DomainEvent{

	private Integer sorteerItemId;
	
	private String naamHuidigeLocatie;
	private String postcodeHuidigeLocatie;
 	private String straatHuidigeLocatie;
 	private String plaatsHuidigeLocatie;
 	private String landHuidigeLocatie;
 	
 	private String naamDoel;
	private String postcodeDoel;
 	private String straatDoel;
 	private String plaatsDoel;
 	private String landDoel;
 	
	private boolean spoed;
	
	public StuurSorteerderDomainEvent(Integer sorteerItemId, Adres huidigeLocatie, Adres doel, boolean spoed) {
		super();
		
		this.sorteerItemId=sorteerItemId;
		
		this.naamHuidigeLocatie=huidigeLocatie.getNaam();
		this.postcodeHuidigeLocatie=huidigeLocatie.getPostcode();
		this.straatHuidigeLocatie=huidigeLocatie.getStraat();
		this.plaatsHuidigeLocatie=huidigeLocatie.getPlaats();
		this.landHuidigeLocatie=huidigeLocatie.getLand();
		
		this.naamDoel=doel.getNaam();
		this.postcodeDoel=doel.getPostcode();
		this.straatDoel=doel.getStraat();
		this.plaatsDoel=doel.getPlaats();
		this.landDoel=doel.getLand();
		
		this.spoed=spoed;
	}
}
