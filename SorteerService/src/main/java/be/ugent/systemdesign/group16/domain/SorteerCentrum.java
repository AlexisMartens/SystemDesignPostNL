package be.ugent.systemdesign.group16.domain;

import java.util.List;
import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SorteerCentrum extends Locatie{

	private List<Locatie> volgendeLocaties;

	@Override
	public SorteerBestemming bepaalVolgendeLocatie(Adres doel) {
		Random rand = new Random();
		return new SorteerBestemming(volgendeLocaties.get(rand.nextInt(volgendeLocaties.size())), rand.nextBoolean(), rand.nextInt(1000));
	}
	
	public SorteerCentrum(Adres adres, List<Locatie> volgendeLocaties) {
		super(adres);
		this.volgendeLocaties=volgendeLocaties;
	}
}
