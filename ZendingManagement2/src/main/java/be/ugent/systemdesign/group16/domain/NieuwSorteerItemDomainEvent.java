package be.ugent.systemdesign.group16.domain;

import java.time.LocalDate;

import be.ugent.systemdesign.group16.domain.seedwork.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NieuwSorteerItemDomainEvent extends DomainEvent {

	private Integer zendingId;
	
	private String typeZending;
		
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
	

	private boolean spoed;
	
	public NieuwSorteerItemDomainEvent(Integer zendingId, String typeZending, String naamAfzender, String postcodeAfzender,
			String straatAfzender, String plaatsAfzender, String landAfzender, String naamOntvanger, String postcodeOntvanger, String straatOntvanger, String plaatsOntvanger,
			String landOntvanger, String naamHuidigeLocatie, String postcodeHuidigeLocatie, String straatHuidigeLocatie, String plaatsHuidigeLocatie, String landHuidigeLocatie, boolean spoed) {
		super();
		this.zendingId=zendingId;
		this.typeZending=typeZending;
		this.naamOntvanger=naamOntvanger;
		this.postcodeOntvanger=postcodeOntvanger;
		this.straatOntvanger=straatOntvanger;
		this.plaatsOntvanger=plaatsOntvanger;
		this.landOntvanger=landOntvanger;
		this.naamAfzender=naamAfzender;
		this.postcodeAfzender=postcodeAfzender;
		this.straatAfzender=straatAfzender;
		this.plaatsAfzender=plaatsAfzender;
		this.landAfzender=landAfzender;
		this.naamHuidigeLocatie=naamHuidigeLocatie;
		this.postcodeHuidigeLocatie=postcodeHuidigeLocatie;
		this.straatHuidigeLocatie=straatHuidigeLocatie;
		this.plaatsHuidigeLocatie=plaatsHuidigeLocatie;
		this.landHuidigeLocatie=landHuidigeLocatie;
		this.spoed=spoed;
	}
}
