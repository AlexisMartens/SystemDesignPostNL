package be.ugent.systemdesign.group16.domain;

import be.ugent.systemdesign.group16.domain.seedwork.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StuurVervoerderDomainEvent extends DomainEvent{
	
	private Integer sorteerItemId;
	private Adres huidigeLocatie;
	private Adres doel;
	private boolean isLaatsteLocatie;
	
	public StuurVervoerderDomainEvent(Integer sorteerItemId, Adres huidigeLocatie, Adres doel, SorteerItemStatus status) {
		super();
		this.sorteerItemId=sorteerItemId;
		this.huidigeLocatie=huidigeLocatie;
		this.doel=doel;
		this.isLaatsteLocatie=(status == SorteerItemStatus.ONDERWEG_NAAR_LAATSTE_LOCATIE);
	}
}
