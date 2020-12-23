package be.ugent.systemdesign.group16.domain;


import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Id;

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
public class Pakket extends AggregateRoot {
	@Id
	@Getter
	private Integer pakketId;

	private PakketGrootte grootte;

	private Adres afzender;
	
	private Adres ontvanger;
	
	private Adres huidigeLocatie;
	
	private String soort;
	
	private boolean spoed;
	
	private PakketStatus status;
	
	
	public Pakket(Pakket _pakket) {
		// default op gemiddeld
		grootte = PakketGrootte.GEMIDDELD;
		ontvanger = _pakket.ontvanger;
		afzender = _pakket.afzender;
		huidigeLocatie = _pakket.huidigeLocatie;
		soort=_pakket.soort;
		spoed=_pakket.spoed;
		
		//TODO: check attr enum PakketStatus
		status=PakketStatus.AANGEMAAKT;
	}
	
	public Pakket(String _naamOntvanger, String _postcodeOntvanger, String _straatOntvanger,
			String _plaatsOntvanger, String _landOntvanger, String _naamAfzender, String _postcodeAfzender,
			String _straatAfzender, String _plaatsAfzender, String _landAfzender, String _naamHuidigeLocatie,
			String _postcodeHuidigeLocatie, String _straatHuidigeLocatie, String _plaatsHuidigeLocatie,
			String _landHuidigeLocatie, String _soort, boolean _spoed, String _status) {
		// default op gemiddeld
		grootte = PakketGrootte.GEMIDDELD;
		afzender = new Adres(_naamAfzender, _postcodeAfzender, _straatAfzender, _plaatsAfzender, _landAfzender);
		ontvanger = new Adres(_naamOntvanger, _postcodeOntvanger, _straatOntvanger, _plaatsOntvanger, _landOntvanger);
		huidigeLocatie = new Adres(_naamHuidigeLocatie,_postcodeHuidigeLocatie, _straatHuidigeLocatie, _plaatsHuidigeLocatie,_landHuidigeLocatie);
		soort = _soort;
		
		spoed =_spoed;
		//TODO: check attr enum PakketStatus
		status=PakketStatus.AANGEMAAKT;	
	}

	
	// TODO: conditie toevoegen??
	public void MaakNieuwSorteerItem() {
		//status = ..
		addDomainEvent(new NieuwSorteerItemDomainEvent(pakketId, grootte.name(), afzender.getNaam(), afzender.getPostcode(), afzender.getStraat(), afzender.getPlaats(), afzender.getPlaats(),
				ontvanger.getNaam(), ontvanger.getPostcode(), ontvanger.getStraat(), ontvanger.getPlaats(), ontvanger.getLand(), 
				huidigeLocatie.getNaam(), huidigeLocatie.getPostcode(), huidigeLocatie.getStraat(), huidigeLocatie.getPlaats(), huidigeLocatie.getLand(),
				soort, spoed, status.name()));
	}
	public void UpdateTrackAndTrace() {
		//status=..
		addDomainEvent(new UpdateTrackAndTraceDomainEvent(pakketId, huidigeLocatie.getNaam(), huidigeLocatie.getPostcode(), huidigeLocatie.getStraat(), huidigeLocatie.getPlaats(),
				huidigeLocatie.getLand(), status.name()));
	}
	

	
}
