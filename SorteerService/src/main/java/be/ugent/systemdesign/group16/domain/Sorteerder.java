package be.ugent.systemdesign.group16.domain;

import be.ugent.systemdesign.group16.domain.seedwork.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sorteerder extends AggregateRoot{
	
	private Integer sorteerderId;
	private String naam;
	private SorteerderStatus status;
	private SorteerOrder order;
	private SorteerCentrum werkLocatie;
	
	public void sorteer(SorteerOrder order) {
		this.status = SorteerderStatus.WORKING;
		this.order = order;
		
		// Nieuw bevestig sorteren event;
		
		this.status = SorteerderStatus.IDLE;
	}
}
