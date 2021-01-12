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
	// zendingId is hetzelfde als een trackId
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
	
	
	public Zending(Integer zendingId, String typeZending, Adres afzender, Adres ontvanger, Adres huidigeLocatie, boolean ophalen, boolean spoed) {
		this.zendingId = zendingId;
		this.typeZending = typeZending;
		this.afzender = afzender;
		this.ontvanger = ontvanger;
		this.huidigeLocatie = huidigeLocatie;

		
		this.spoed = spoed;
		this.status = ZendingStatus.AANGEMAAKT;
		this.ophalenBijKlantThuis = ophalen;
		if(ophalen) {
			this.status = ZendingStatus.KLAAR_OM_OP_TE_HALEN;
			this.huidigeLocatie = afzender;
		}
		this.aanmaakDatum = LocalDate.now();
	}
	

	public Zending(Integer zendingId, String typeZending, Adres afzender, Adres ontvanger, boolean ophalen, boolean spoed) {
		this.zendingId = zendingId;
		this.typeZending = typeZending;
		this.afzender = afzender;
		this.ontvanger = ontvanger;
		
		this.spoed = spoed;
		this.status = ZendingStatus.AANGEMAAKT;
		this.ophalenBijKlantThuis = ophalen;
		if(ophalen) {
			this.status = ZendingStatus.KLAAR_OM_OP_TE_HALEN;
			this.huidigeLocatie = afzender;
		}
		this.aanmaakDatum = LocalDate.now();
	}
	
	
	public Zending(Integer zendingId, String typeZending, Adres afzender, Adres ontvanger, Adres huidigeLocatie, boolean spoed) {
		this.zendingId = zendingId;
		this.typeZending = typeZending;
		this.afzender = afzender;
		this.ontvanger = ontvanger;
		this.huidigeLocatie = huidigeLocatie;

		
		this.spoed = spoed;
		this.status = ZendingStatus.AANGEMAAKT;
		
		this.ophalenBijKlantThuis = false;
		
		this.aanmaakDatum = LocalDate.now();
	}
	
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
	
	public void stuurKoerier(Adres locatie) {
		addDomainEvent(new StuurKoerierDomainEvent(zendingId,
				typeZending,
				huidigeLocatie.getNaam(), huidigeLocatie.getPostcode(), huidigeLocatie.getStraat(), huidigeLocatie.getPlaats(), huidigeLocatie.getLand(), 
				locatie.getNaam(), locatie.getPostcode(),locatie.getStraat(),locatie.getPlaats(), locatie.getLand(),
				spoed));
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
