package be.ugent.systemdesign.group16.domain;


import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor

//TODO: ??? @AllArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor()
public class Zending{// extends AggregateRoot {
	
	private Integer zendingId;

	private String typeZending;

	private Adres ontvanger;

	private Adres afzender;
	// extra:
	// true: koerier moet zending ophalen bij klant thuis
	// false: klant moet zending zelf brengen naar ophaalpunt
	private boolean ophalenBijKlantThuis;
	// extra:
	private Adres huidigeLocatie;

	private LocalDate aanmaakDatum;

	private ZendingStatus status;

	private boolean spoed;

	private boolean extern;

	//TODO: hoe integreren?
	/*private KoerierService koerierService;
	private SorteerItemManagement sorteerItemManagement;*/

	
	public Zending(String _typeZending, String _huidigeLocatieNaam, String _huidigePostcode, String _huidigeStraat, String _huidigePlaats, String _huidigLand, String _naamOntvanger, String _postcodeOntvanger, String _straatOntvanger, String _plaatsOntvanger, String _landOntvanger, String _naamAfzender, String _postcodeAfzender, String _straatAfzender, String _plaatsAfzender, String _landAfzender, boolean _ophalenBijKlant, boolean _spoed, boolean _extern) {
		typeZending=_typeZending;
		huidigeLocatie = new Adres(_huidigeLocatieNaam, _huidigePostcode, _huidigeStraat, _huidigePlaats, _huidigLand);
		// vanuitgaande dat Bestelling ontvanger en afzender correct instelde volgens al dan niet retour:
		afzender = new Adres(_naamAfzender, _postcodeAfzender, _straatAfzender, _plaatsAfzender, _landAfzender);
		ontvanger = new Adres(_naamOntvanger, _postcodeOntvanger, _straatOntvanger, _plaatsOntvanger, _landOntvanger);
		
		ophalenBijKlantThuis = _ophalenBijKlant;
		aanmaakDatum = LocalDate.now();
		status=ZendingStatus.AANGEMAAKT;
		spoed=_spoed;
		extern=_extern;
		
		if(!huidigeLocatie.isCorrectAdres()) {
			throw new GeenGeldigAdresException();
		}
	}
	// TODO: retour ook hier verwerken??
	public Zending(Zending _zending, String _huidigeLocatieNaam, String _huidigePostcode, String _huidigeStraat, String _huidigePlaats, String _huidigLand) {
		typeZending =_zending.typeZending;
		huidigeLocatie = new Adres(_huidigeLocatieNaam, _huidigePostcode, _huidigeStraat, _huidigePlaats, _huidigLand);
		aanmaakDatum = LocalDate.now();
		status=ZendingStatus.AANGEMAAKT;
		// vanuitgaande dat Bestelling ontvanger en afzender correct instelde volgens al dan niet retour:
		ontvanger = _zending.ontvanger;
		afzender = _zending.afzender;
		
		spoed=_zending.spoed;
		extern=_zending.extern;
		ophalenBijKlantThuis = _zending.ophalenBijKlantThuis;
		
		if(!huidigeLocatie.isCorrectAdres()) {
			throw new GeenGeldigAdresException();
		}		
	}

	// TODO: verwerk methode(s)?
}
