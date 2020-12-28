package be.ugent.systemdesign.group16.domain;


import java.time.LocalDate;
import java.time.LocalDateTime;

import be.ugent.systemdesign.group16.domain.seedwork.AggregateRoot;
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
@AllArgsConstructor()
public class Zending extends AggregateRoot {
	
	private Integer zendingId;

	private String typeZending;

	private Adres ontvanger;

	private Adres afzender;

	// true: koerier moet zending ophalen bij klant thuis
	// false: klant moet zending brengen naar ophaalpunt
	private boolean ophalenBijKlantThuis;

	private Adres huidigeLocatie;

	private LocalDate aanmaakDatum;

	private ZendingStatus status;

	private boolean spoed;
	
	public Zending(Zending _z) {
		typeZending = _z.typeZending;
		ontvanger = _z.ontvanger;
		afzender = _z.afzender;
		aanmaakDatum = LocalDate.now();
		spoed=_z.spoed;
		huidigeLocatie = null;
		
		ophalenBijKlantThuis = _z.ophalenBijKlantThuis;
		status = ZendingStatus.AANGEMAAKT;
		if(ophalenBijKlantThuis) {
			status = ZendingStatus.KLAAR_OM_OP_TE_HALEN;
			// adres van klant thuis (afzender) naar koerier
			huidigeLocatie = afzender;
		}
		if(!ontvanger.isCorrectAdres()) {
			throw new GeenGeldigAdresException();
		}
	}

	public Zending(String _typeZending,  String _naamOntvanger, String _postcodeOntvanger, String _straatOntvanger, String _plaatsOntvanger, String _landOntvanger, String _naamAfzender, String _postcodeAfzender, String _straatAfzender, String _plaatsAfzender, String _landAfzender, boolean _ophalenBijKlant, boolean _spoed) {
		typeZending=_typeZending;
		//huidigeLocatie = new Adres(_huidigeLocatieNaam, _huidigePostcode, _huidigeStraat, _huidigePlaats, _huidigLand);
		afzender = new Adres(_naamAfzender, _postcodeAfzender, _straatAfzender, _plaatsAfzender, _landAfzender);
		ontvanger = new Adres(_naamOntvanger, _postcodeOntvanger, _straatOntvanger, _plaatsOntvanger, _landOntvanger);
		
		ophalenBijKlantThuis = _ophalenBijKlant;
		aanmaakDatum = LocalDate.now();
		status=ZendingStatus.AANGEMAAKT;
		spoed=_spoed;
		if(ophalenBijKlantThuis) {
			status = ZendingStatus.KLAAR_OM_OP_TE_HALEN;
			// adres van klant thuis (afzender) naar koerier
			huidigeLocatie = afzender;
		}

	}
	
	public Zending(Zending _zending, String _huidigeLocatieNaam, String _huidigePostcode, String _huidigeStraat, String _huidigePlaats, String _huidigLand) {
		typeZending =_zending.typeZending;
		huidigeLocatie = new Adres(_huidigeLocatieNaam, _huidigePostcode, _huidigeStraat, _huidigePlaats, _huidigLand);
		aanmaakDatum = LocalDate.now();
		status=ZendingStatus.AANGEMAAKT;

		ontvanger = _zending.ontvanger;
		afzender = _zending.afzender;
		
		spoed=_zending.spoed;
		ophalenBijKlantThuis = _zending.ophalenBijKlantThuis;
		if(ophalenBijKlantThuis) {
			status=ZendingStatus.KLAAR_OM_OP_TE_HALEN;
		}
		if(!huidigeLocatie.isCorrectAdres()) {
			throw new GeenGeldigAdresException();
		}		
	}
	
	public void stuurKoerier() {
		addDomainEvent(new KlaarVoorKoerierDomainEvent(zendingId, 
				typeZending, afzender.getNaam(), afzender.getPostcode(), afzender.getStraat(), afzender.getPlaats(), afzender.getLand(), 
				ontvanger.getNaam(), ontvanger.getPostcode(),ontvanger.getStraat(),ontvanger.getPlaats(), ontvanger.getLand(), 
				huidigeLocatie.getNaam(), huidigeLocatie.getPostcode(), huidigeLocatie.getStraat(), huidigeLocatie.getPlaats(), huidigeLocatie.getLand(), spoed
				));
		status=ZendingStatus.KLAAR_OM_OP_TE_HALEN;	
	}
	
	public void maakNieuwSorteerItem() {
		addDomainEvent(new NieuwSorteerItemDomainEvent(zendingId, 
				typeZending, afzender.getNaam(), afzender.getPostcode(), afzender.getStraat(), afzender.getPlaats(), afzender.getLand(), 
				ontvanger.getNaam(), ontvanger.getPostcode(),ontvanger.getStraat(),ontvanger.getPlaats(), ontvanger.getLand(), 
				huidigeLocatie.getNaam(), huidigeLocatie.getPostcode(), huidigeLocatie.getStraat(), huidigeLocatie.getPlaats(), huidigeLocatie.getLand(), spoed
				));
		status=ZendingStatus.AFGELEVERD;	
	}
}
