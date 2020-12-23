package be.ugent.systemdesign.group16.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import be.ugent.systemdesign.group16.domain.seedwork.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class UpdateTrackAndTraceDomainEvent extends DomainEvent {
	private Integer pakketId;
	
	private String naam;
	private String postcode;
	private String straat;
	private String plaats;
	private String land;
	
	private String status;
}
