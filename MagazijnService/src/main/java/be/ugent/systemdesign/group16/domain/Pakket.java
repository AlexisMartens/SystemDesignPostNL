package be.ugent.systemdesign.group16.domain;

import javax.persistence.Id;

import be.ugent.systemdesign.group16.domain.seedwork.AggregateRoot;
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
public class Pakket extends AggregateRoot {
	@Id
	@Getter
	private Integer pakketId;

	private Adres afzender;
	
	private Adres ontvanger;
	
	private Adres huidigeLocatie;
	
	private String soort;
	
	private boolean spoed;
	
	private PakketStatus status;
	
	private boolean ophalenBijKlant;


	public Pakket(Integer id, String _naamOntvanger, String _postcodeOntvanger, String _straatOntvanger,
			String _plaatsOntvanger, String _landOntvanger, String _naamAfzender, String _postcodeAfzender,
			String _straatAfzender, String _plaatsAfzender, String _landAfzender
			, String _soort, boolean _ophalen, boolean _spoed) {
		this.pakketId = id;
		afzender = new Adres(_naamAfzender, _postcodeAfzender, _straatAfzender, _plaatsAfzender, _landAfzender);
		ontvanger = new Adres(_naamOntvanger, _postcodeOntvanger, _straatOntvanger, _plaatsOntvanger, _landOntvanger);
		this.ophalenBijKlant = _ophalen;
		
		// Merk op: vereenvoudiging implementatie: ophalenBijKlant altijd true dus huidigeLocatie=afzender
		huidigeLocatie = afzender;
				
		soort = _soort;
		spoed =_spoed;
		status=PakketStatus.AANGEMAAKT;	
	}
	
	public Pakket(Integer id, String _naamOntvanger, String _postcodeOntvanger, String _straatOntvanger,
			String _plaatsOntvanger, String _landOntvanger, String _naamAfzender, String _postcodeAfzender,
			String _straatAfzender, String _plaatsAfzender, String _landAfzender, String _soort, boolean _spoed) {
		this.pakketId = id;
		afzender = new Adres(_naamAfzender, _postcodeAfzender, _straatAfzender, _plaatsAfzender, _landAfzender);
		ontvanger = new Adres(_naamOntvanger, _postcodeOntvanger, _straatOntvanger, _plaatsOntvanger, _landOntvanger);
		soort = _soort;
		// Merk op: vereenvoudiging implementatie: ophalenBijKlant altijd true dus huidigeLocatie=afzender
		huidigeLocatie = afzender;
		spoed =_spoed;
		status=PakketStatus.AANGEMAAKT;	
	}
	
	// TODO: conditie toevoegen??
	public void MaakNieuwSorteerItem() {
		//status = ..
		addDomainEvent(new NieuwSorteerItemDomainEvent(pakketId, afzender.getNaam(), afzender.getPostcode(), afzender.getStraat(), afzender.getPlaats(), afzender.getPlaats(),
				ontvanger.getNaam(), ontvanger.getPostcode(), ontvanger.getStraat(), ontvanger.getPlaats(), ontvanger.getLand(), 
				huidigeLocatie.getNaam(), huidigeLocatie.getPostcode(), huidigeLocatie.getStraat(), huidigeLocatie.getPlaats(), huidigeLocatie.getLand(),
				soort, spoed));
	}
	public void UpdateTrackAndTrace() {
		// Brieven worden niet getracked en moeten dus niet geüpdatet worden
		if(soort.equals("PAKKET")) {
			addDomainEvent(new UpdateTrackAndTraceDomainEvent(pakketId, huidigeLocatie.getNaam(), huidigeLocatie.getPostcode(), huidigeLocatie.getStraat(), huidigeLocatie.getPlaats(),
					huidigeLocatie.getLand(), status.name()));
		}
	
	}
	
}
