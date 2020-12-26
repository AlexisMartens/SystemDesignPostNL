package be.ugent.systemdesign.group16.domain;

import be.ugent.systemdesign.group16.domain.seedwork.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vervoerder extends AggregateRoot{
	
	private Integer vervoerderId;
	private String naam;
	private VervoerStatus status;
	
	private VervoerOrder order;
	
	public void vervoer(VervoerOrder order) {
		this.status=VervoerStatus.WORKING;
		this.order=order;
		
		// Vervoeren afgelopen
		addDomainEvent(new BevestigVervoerenItemDomainEvent(order.getSorteerItemId(), order.getNaar().getNaam(),
				order.getNaar().getPostcode(), order.getNaar().getStraat(), order.getNaar().getPlaats(), order.getNaar().getLand()));
		
		this.status=VervoerStatus.IDLE;
	}
}
