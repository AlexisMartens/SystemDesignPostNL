package be.ugent.systemdesign.group16.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Locatie {
	
	private Adres eigenLocatie;
	
	public abstract SorteerBestemming bepaalVolgendeLocatie(Adres doel);
}
