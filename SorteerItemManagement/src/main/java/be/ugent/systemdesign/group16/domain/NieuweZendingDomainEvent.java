package be.ugent.systemdesign.group16.domain;

import java.time.LocalDate;

import be.ugent.systemdesign.group16.domain.seedwork.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NieuweZendingDomainEvent extends DomainEvent{
	
	private Integer zendingId;
	
	private String naamAfzender;
	private String postcodeAfzender;
	private String straatAfzender;
	private String plaatsAfzender;
	private String landAfzender;
	
	private String naamOntvanger;
	private String postcodeOntvanger;
	private String straatOntvanger;
	private String plaatsOntvanger;
	private String landOntvanger;

	private String naamHuidigeLocatie;
	private String postcodeHuidigeLocatie;
	private String straatHuidigeLocatie;
	private String plaatsHuidigeLocatie;
	private String landHuidigeLocatie;
	
	private String soort;
	private boolean spoed;
	private LocalDate aanmaakDatum;
	
	public NieuweZendingDomainEvent(SorteerItem i) {
		super();
		
		this.zendingId = i.getTrackId();
		
		this.naamAfzender=i.getAfkomst().getNaam();
		this.postcodeAfzender=i.getAfkomst().getPostcode();
		this.straatAfzender=i.getAfkomst().getStraat();
		this.plaatsAfzender=i.getAfkomst().getPlaats();
		this.landAfzender=i.getAfkomst().getLand();
		
		this.naamHuidigeLocatie=i.getHuidigeLocatie().getNaam();
		this.postcodeHuidigeLocatie=i.getHuidigeLocatie().getPostcode();
		this.straatHuidigeLocatie=i.getHuidigeLocatie().getStraat();
		this.plaatsHuidigeLocatie=i.getHuidigeLocatie().getPlaats();
		this.landHuidigeLocatie=i.getHuidigeLocatie().getLand();
		
		this.naamOntvanger=i.getDoel().getNaam();
		this.postcodeOntvanger=i.getDoel().getPostcode();
		this.straatOntvanger=i.getDoel().getStraat();
		this.plaatsOntvanger=i.getDoel().getPlaats();
		this.landOntvanger=i.getDoel().getLand();
		
		this.soort=i.getSoort().name();
		this.spoed=i.isSpoed();
		this.aanmaakDatum=i.getAanmaakDatum();
	}
}
