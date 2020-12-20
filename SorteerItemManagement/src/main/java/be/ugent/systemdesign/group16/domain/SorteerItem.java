package be.ugent.systemdesign.group16.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SorteerItem {
	
	private Integer sorteerItemId;
	private Adres doel, afkomst;
	private Soort soort;
	private boolean inCentrum;
	private boolean isBijLaatsteDepot;
}
