package be.ugent.systemdesign.group16.domain;

import be.ugent.systemdesign.group16.domain.seedwork.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BevestigVervoerenItemDomainEvent extends DomainEvent{

	private Integer sorteerItemId;
	
	private String naamNieuweLocatie;
	private String postcodeNieuweLocatie;
 	private String straatNieuweLocatie;
 	private String plaatsNieuweLocatie;
 	private String landNieuweLocatie;
}
