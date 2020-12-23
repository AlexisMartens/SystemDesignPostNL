package be.ugent.systemdesign.group16.domain;

import be.ugent.systemdesign.group16.domain.seedwork.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NieuwSorteerItemDomainEvent extends DomainEvent {
	private Integer pakketId;

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

	public NieuwSorteerItemDomainEvent(Integer pakketId, String naamAfzender, String postcodeAfzender,
			String straatAfzender, String plaatsAfzender, String landAfzender, String naamOntvanger,
			String postcodeOntvanger, String straatOntvanger, String plaatsOntvanger, String landOntvanger,
			String naamHuidigeLocatie, String postcodeHuidigeLocatie, String straatHuidigeLocatie,
			String plaatsHuidigeLocatie, String landHuidigeLocatie, String soort, boolean spoed) {
		super();
		this.pakketId = pakketId;
		this.naamAfzender = naamAfzender;
		this.postcodeAfzender = postcodeAfzender;
		this.straatAfzender = straatAfzender;
		this.plaatsAfzender = plaatsAfzender;
		this.landAfzender = landAfzender;
		this.naamOntvanger = naamOntvanger;
		this.postcodeOntvanger = postcodeOntvanger;
		this.straatOntvanger = straatOntvanger;
		this.plaatsOntvanger = plaatsOntvanger;
		this.landOntvanger = landOntvanger;
		this.naamHuidigeLocatie = naamHuidigeLocatie;
		this.postcodeHuidigeLocatie = postcodeHuidigeLocatie;
		this.straatHuidigeLocatie = straatHuidigeLocatie;
		this.plaatsHuidigeLocatie = plaatsHuidigeLocatie;
		this.landHuidigeLocatie = landHuidigeLocatie;
		this.soort = soort;
		this.spoed = spoed;
	}
}
	