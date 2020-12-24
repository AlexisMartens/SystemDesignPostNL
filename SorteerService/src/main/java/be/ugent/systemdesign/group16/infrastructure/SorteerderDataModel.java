package be.ugent.systemdesign.group16.infrastructure;

import be.ugent.systemdesign.group16.domain.Locatie;
import be.ugent.systemdesign.group16.domain.SorteerOrder;
import be.ugent.systemdesign.group16.domain.SorteerderStatus;

@Entity
public class SorteerderDataModel {

	private Integer sorteerderId;
	private String naam;
	private String status;
	private SorteerOrder order;
	private Locatie werkLocatie;
}
