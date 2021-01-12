package be.ugent.systemdesign.group16.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@Builder
//@NoArgsConstructor
//@AllArgsConstructor werkt niet 
public class Adres {

	private String naam;
	private String postcode;
	private String straat;
	private String plaats;
	private String land;
	// constructor hoeft niet expliciet geschreven worden want Annotatie (@AllArgsConstructor) doet dit al
	public Adres(String naam, String postcode, String straat, String plaats, String land) {
		this.naam = naam;
		this.postcode = postcode;
		this.straat = straat;
		this.plaats = plaats;
		this.land = land;
	}

	public boolean isCorrectAdres() {
		return !postcode.isEmpty() && !straat.isEmpty() && !plaats.isEmpty() && !land.isEmpty();
	}
	
	
}