package be.ugent.systemdesign.group16.infrastructure;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BestellingDataModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Integer bestellingId;
	
	private String typeBestelling;
	
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
	
	private boolean ophalen;
	
	private LocalDate aanmaakDatum;
	
	private String status;
	
	private Boolean spoed;
}
