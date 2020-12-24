package be.ugent.systemdesign.group16.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import be.ugent.systemdesign.group16.domain.Adres;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class BevestigVervoerenItemEvent {

	private Integer sorteerItemId;
	
	private String naamNieuweLocatie;
	private String postcodeNieuweLocatie;
 	private String straatNieuweLocatie;
 	private String plaatsNieuweLocatie;
 	private String landNieuweLocatie;
}
