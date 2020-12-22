package be.ugent.systemdesign.group16.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Adres {
	private String naam;
	private String postcode;
	private String straat;
	private String plaats;
	private String land;
	
	public boolean isCorrectAdres() {
		return !postcode.isEmpty() && !straat.isEmpty() && !plaats.isEmpty() && !land.isEmpty();
	}
}
