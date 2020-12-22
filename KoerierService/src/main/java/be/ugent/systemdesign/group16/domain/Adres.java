package be.ugent.systemdesign.group16.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
