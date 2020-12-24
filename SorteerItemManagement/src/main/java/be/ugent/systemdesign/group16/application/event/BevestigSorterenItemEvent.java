package be.ugent.systemdesign.group16.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import be.ugent.systemdesign.group16.domain.Adres;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class BevestigSorterenItemEvent {
	
	private Integer sorteerItemId;
	
	private String naamVolgendeLocatie;
	private String postcodeVolgendeLocatie;
 	private String straatVolgendeLocatie;
 	private String plaatsVolgendeLocatie;
 	private String landVolgendeLocatie;

	private Integer batchId;
	private boolean laatsteLocatie;
}
