package be.ugent.systemdesign.group16.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SorteerLocatie {
	private static final Random r = new Random();
	private static final List<Adres> adressen = new ArrayList<Adres>(){
		{
			add(new Adres("Sorteercentrum Gent","9000","Gentstraat 10","Gent","Belgie"));
			add(new Adres("Sorteercentrum Nevele","9100","Nevelestraat 10","Nevele","Belgie"));
			add(new Adres("Sorteercentrum Brugge","8000","Bruggestraat 10","Brugge","Belgie"));
		}
	};
	
	public static Adres getSorteerLocatie() {
		return adressen.get(r.nextInt(adressen.size()));
	}
}
