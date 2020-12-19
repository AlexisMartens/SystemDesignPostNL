package be.ugent.systemdesign.group16.domain;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.ugent.systemdesign.group16.application.event.BestellingEventListener;
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
public class Koerier extends AggregateRoot {
	
	private Integer KoerierId;
	
	private String typeBestelling;
	
	private Adres ontvanger;
	
	private Adres afzender;
	
	private boolean ophalen;
	
	private LocalDate aanmaakDatum;
	
	private BestellingStatus status;
	
	private boolean spoed;
	
	private boolean extern;
	
	private ExterneLeveringService externeLeveringService;
	
	public Koerier(String _typeBestelling, String _naamOntvanger, String _postcodeOntvanger, String _straatOntvanger, String _plaatsOntvanger, String _landOntvanger, String _naamAfzender, String _postcodeAfzender, String _straatAfzender, String _plaatsAfzender, String _landAfzender, boolean _spoed, boolean _extern, String _externeLeveringService) {
		typeBestelling=_typeBestelling;
		afzender = new Adres(_naamAfzender, _postcodeAfzender, _straatAfzender, _plaatsAfzender, _landAfzender);
		ontvanger = new Adres(_naamOntvanger, _postcodeOntvanger, _straatOntvanger, _plaatsOntvanger, _landOntvanger);
		aanmaakDatum = LocalDate.now();
		status=BestellingStatus.AANGEMAAKT;
		spoed=_spoed;
		extern=_extern;
		if(extern) {
			try {
				externeLeveringService=ExterneLeveringService.valueOf(_externeLeveringService);
			} catch (IllegalArgumentException e) {
				throw new GeenBestaandeExterneLeveringServiceException();
			}
		}
		if(!ontvanger.isCorrectAdres()) {
			throw new GeenGeldigeOntvangerException();
		}
	}
	
	public Koerier(Koerier _bestelling, boolean retour) {
		typeBestelling=_bestelling.typeBestelling;
		if(retour) {
			afzender = _bestelling.ontvanger;
			ontvanger = _bestelling.afzender;
		}
		else {
			ontvanger = _bestelling.ontvanger;
			afzender = _bestelling.afzender;
		}
		aanmaakDatum = LocalDate.now();
		status=BestellingStatus.AANGEMAAKT;
		spoed=_bestelling.spoed;
		extern=_bestelling.extern;
		if(extern) {
			try {
				externeLeveringService=ExterneLeveringService.valueOf(_bestelling.externeLeveringService == null ? null : _bestelling.externeLeveringService.name() );
			} catch (IllegalArgumentException e) {
				throw new GeenBestaandeExterneLeveringServiceException();
			} 
		}
		if(!ontvanger.isCorrectAdres()) {
			throw new GeenGeldigeOntvangerException();
		}
		
	}
	
	public void Verwerk() {
		addDomainEvent(new NieuweTrackAndTraceDomainEvent(bestellingId.toString(), status.name()));
		if(extern) {
			
			addDomainEvent(new ExterneBestellingDomainEvent(bestellingId.toString(), typeBestelling, ontvanger.getNaam(), ontvanger.getPostcode(),ontvanger.getStraat(),ontvanger.getPlaats(), ontvanger.getLand(), afzender.getNaam(), afzender.getPostcode(), afzender.getStraat(), afzender.getPlaats(), afzender.getLand(), ophalen, status.toString(), spoed, extern, externeLeveringService.toString()));
		}
		else {
			//event naar zendingmgmt
		}
		status=BestellingStatus.VERWERKT;
		
	
	}
}
