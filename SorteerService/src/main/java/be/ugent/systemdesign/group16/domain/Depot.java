package be.ugent.systemdesign.group16.domain;

import java.util.List;
import java.util.Random;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Depot extends Locatie{

	private Locatie volgendeLocatie;

	public Depot(Adres adres, Locatie volgendeLocatie) {
		super(adres);
		this.volgendeLocatie=volgendeLocatie;
	}
	@Override
	public SorteerBestemming bepaalVolgendeLocatie(Adres doel) {
		Random rand = new Random();
		return new SorteerBestemming(volgendeLocatie, false, rand.nextInt(1000));
	}
}
