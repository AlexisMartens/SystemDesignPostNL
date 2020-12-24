package be.ugent.systemdesign.group16.domain;

import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SorteerCentrum {
	
	private Integer locatieId;
	private Adres eigenLocatie;
	private Adres volgendeLocatie;
	
	public SorteerBestemming bepaalVolgendeLocatie(Adres doel) {
		Random rand = new Random();
		return new SorteerBestemming(volgendeLocatie, rand.nextBoolean(), rand.nextInt(1000));
	}
}
