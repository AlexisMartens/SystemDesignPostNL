package be.ugent.systemdesign.group16.infrastructure;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class AdresDataModel {
	private String naam;
	private String postcode;
	private String straat;
	private String plaats;
	private String land;
}
