package be.ugent.systemdesign.group16.application.event;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExterneBestellingDomainEvent{
	
	

	private String bestellingId;
	
	private String typeBestelling;
	
	private String naamOntvanger;
	private String postcodeOntvanger;
	private String straatOntvanger;
	private String plaatsOntvanger;
	private String landOntvanger;

	private String naamAfzender;
	private String postcodeAfzender;
	private String straatAfzender;
	private String plaatsAfzender;
	private String landAfzender;
	
	private boolean ophalen;
	
	private String status;
	
	private boolean spoed;
	
	private boolean extern;
	
	private String externeLeveringService;
	
	public ExterneBestellingDomainEvent(String bestellingId2, String typeBestelling2, String naam, String postcode,
			String straat, String plaats, String land, String naam2, String postcode2, String straat2, String plaats2,
			String land2, boolean ophalen2, String status2, boolean spoed2, boolean extern2,
			String externeLeveringService2) {
		this.bestellingId=bestellingId2;
		this.typeBestelling=typeBestelling2;
		this.naamOntvanger=naam;
		this.postcodeOntvanger=postcode;
		this.straatOntvanger=straat;
		this.plaatsOntvanger=plaats;
		this.landOntvanger=land;
		this.naamAfzender=naam2;
		this.postcodeAfzender=postcode2;
		this.straatAfzender=straat2;
		this.plaatsAfzender=plaats2;
		this.landAfzender=land2;
		this.ophalen=ophalen2;
		this.status=status2;
		this.spoed=spoed2;
		this.extern=extern2;
		this.externeLeveringService=externeLeveringService2;
		
	}
}
