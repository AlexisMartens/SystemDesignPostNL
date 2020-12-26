package be.ugent.systemdesign.group16.infrastructure;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SorteerCentrumDataModel {

	@Id
	@GeneratedValue
	private Integer locatieId;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "naam", column = @Column(name = "naam_adres")),
		@AttributeOverride(name = "postcode", column = @Column(name = "postcode_adres")),
		@AttributeOverride(name = "straat", column = @Column(name = "straat_adres")),
		@AttributeOverride(name = "plaats", column = @Column(name = "plaats_adres")),
		@AttributeOverride(name = "land", column = @Column(name = "land_adres"))
	})
	private AdresDataModel adres; 
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "naam", column = @Column(name = "naam_volgende_locatie")),
		@AttributeOverride(name = "postcode", column = @Column(name = "postcode_volgende_locatie")),
		@AttributeOverride(name = "straat", column = @Column(name = "straat_volgende_locatie")),
		@AttributeOverride(name = "plaats", column = @Column(name = "plaats_volgende_locatie")),
		@AttributeOverride(name = "land", column = @Column(name = "land_volgende_locatie"))
	})
	private AdresDataModel volgendeLocatie;
}
