package be.ugent.systemdesign.group16.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TrackAndTrace {

	@Id
	@Getter
	private Integer bestellingId;
	
	private String naam;
	private String postcode;
	private String straat;
	private String plaats;
	private String land;
	
	private String status;
	
	public void update(String _naam, String _postcode, String _straat,
			String _plaats, String _land, String _status) {
		naam=_naam;
		postcode=_postcode;
		straat=_straat;
		plaats=_plaats;
		land=_land;
		status=_status;
	}
}
