package be.ugent.systemdesign.group16.application.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class UpdateTrackAndTraceEvent {
	private Integer bestellingId;
	
	private String naam;
	private String postcode;
	private String straat;
	private String plaats;
	private String land;
	
	private String status;
}
