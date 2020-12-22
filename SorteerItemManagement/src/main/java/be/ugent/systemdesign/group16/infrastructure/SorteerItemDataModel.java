package be.ugent.systemdesign.group16.infrastructure;

import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;

import org.hibernate.annotations.Entity;
import org.springframework.data.annotation.Id;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Soort;
import be.ugent.systemdesign.group16.domain.SorteerItemStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SorteerItemDataModel{
	
	@Id
	@Getter
	private Integer sorteerItemId;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "naam", column = @Column(name = "naam_doel")),
		@AttributeOverride(name = "postcode", column = @Column(name = "postcode_doel")),
		@AttributeOverride(name = "straat", column = @Column(name = "straat_doel")),
		@AttributeOverride(name = "plaats", column = @Column(name = "plaats_doel")),
		@AttributeOverride(name = "land", column = @Column(name = "land_doel"))
	})
	private Adres doel;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "naam", column = @Column(name = "naam_afkomst")),
		@AttributeOverride(name = "postcode", column = @Column(name = "postcode_afkomst")),
		@AttributeOverride(name = "straat", column = @Column(name = "straat_afkomst")),
		@AttributeOverride(name = "plaats", column = @Column(name = "plaats_afkomst")),
		@AttributeOverride(name = "land", column = @Column(name = "land_afkomst"))
	})
	private Adres afkomst;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "naam", column = @Column(name = "naam_huidige_locatie")),
		@AttributeOverride(name = "postcode", column = @Column(name = "postcode_huidige_locatie")),
		@AttributeOverride(name = "straat", column = @Column(name = "straat_huidige_locatie")),
		@AttributeOverride(name = "plaats", column = @Column(name = "plaats_huidige_locatie")),
		@AttributeOverride(name = "land", column = @Column(name = "land_huidige_locatie"))
	})
	private Adres huidigeLocatie;
	
	private Soort soort;
	private boolean spoed;
	private String status;
	private LocalDate aanmaakDatum;
}