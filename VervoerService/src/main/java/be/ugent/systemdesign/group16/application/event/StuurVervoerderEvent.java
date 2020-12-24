package be.ugent.systemdesign.group16.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StuurVervoerderEvent {

	private Integer sorteerItemId;
	
	private String naamHuidigeLocatie;
	private String postcodeHuidigeLocatie;
 	private String straatHuidigeLocatie;
 	private String plaatsHuidigeLocatie;
 	private String landHuidigeLocatie;
 	
 	private String naamVolgendeLocatie;
	private String postcodeVolgendeLocatie;
 	private String straatVolgendeLocatie;
 	private String plaatsVolgendeLocatie;
 	private String landVolgendeLocatie;
 	
	private Integer batchId;
	private boolean spoed;
}