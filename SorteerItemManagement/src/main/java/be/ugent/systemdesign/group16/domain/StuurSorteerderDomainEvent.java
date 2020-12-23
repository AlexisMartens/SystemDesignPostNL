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
	
	private Adres huidigeLocatie;
	private Adres doel;

	private boolean spoed;
	
	public StuurSorteerderDomainEvent(SorteerItem i) {
		super();
		this.sorteerItemId=i.getSorteerItemId();
		this.huidigeLocatie=i.getHuidigeLocatie();
		this.doel=i.getDoel();
		this.spoed=i.isSpoed();
	}
}
