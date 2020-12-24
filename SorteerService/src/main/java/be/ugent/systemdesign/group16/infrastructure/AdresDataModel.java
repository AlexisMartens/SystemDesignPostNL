package be.ugent.systemdesign.group16.infrastructure;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdresDataModel {

	private String naam, postcode, straat, plaats, land;

}
