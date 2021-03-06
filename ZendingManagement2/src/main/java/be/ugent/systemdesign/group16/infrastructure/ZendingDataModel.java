package be.ugent.systemdesign.group16.infrastructure;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.ZendingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ZendingDataModel {

	@Id
	@Getter
	//zendingId is hetzelfde als trackId
	private Integer zendingId;
	
	private String typeZending;
	
	private String naamHuidigeLocatie;
	private String postcodeHuidigeLocatie;
	private String straatHuidigeLocatie;
	private String plaatsHuidigeLocatie;
	private String landHuidigeLocatie;
	
	private String naamAfzender;
	private String postcodeAfzender;
	private String straatAfzender;
	private String plaatsAfzender;
	private String landAfzender;
	
	private String naamOntvanger;
	private String postcodeOntvanger;
	private String straatOntvanger;
	private String plaatsOntvanger;
	private String landOntvanger;
		
	private boolean ophalenBijKlantThuis;
	
	private LocalDate aanmaakDatum;
	
	private String status;
	
	private Boolean spoed;	
}
