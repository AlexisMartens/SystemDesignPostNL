package be.ugent.systemdesign.group16.domain;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class Koerier {
	
	private Integer koerierId;
	
	private String naam;
	
	private String postcodeRonde;
	
	private Integer vervoercapaciteit;
    
	public Koerier(Integer koerierId, String naam, String postcodeRonde, Integer vervoercapaciteit) {
		this.koerierId = koerierId;
		this.naam = naam;
		this.postcodeRonde = postcodeRonde;
		this.vervoercapaciteit = vervoercapaciteit;
	}

}