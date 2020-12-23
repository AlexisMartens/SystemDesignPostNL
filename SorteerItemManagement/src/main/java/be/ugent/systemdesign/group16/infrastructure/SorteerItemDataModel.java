package be.ugent.systemdesign.group16.infrastructure;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

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
@AllArgsConstructor
public class SorteerItemDataModel{
	
	@Id
	@Getter
	@GeneratedValue
	// Id gebruikt als primary key in DB, niet gelijk aan trackId omdat er vanuit Sorteerder nieuwe 
	// (brief)SorteerItems worden aangemaakt die niet getracked worden 
	private Integer sorteerItemId;
	
	private Integer trackId;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "naam", column = @Column(name = "naam_doel")),
		@AttributeOverride(name = "postcode", column = @Column(name = "postcode_doel")),
		@AttributeOverride(name = "straat", column = @Column(name = "straat_doel")),
		@AttributeOverride(name = "plaats", column = @Column(name = "plaats_doel")),
		@AttributeOverride(name = "land", column = @Column(name = "land_doel"))
	})
	private AdresDataModel doel;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "naam", column = @Column(name = "naam_afkomst")),
		@AttributeOverride(name = "postcode", column = @Column(name = "postcode_afkomst")),
		@AttributeOverride(name = "straat", column = @Column(name = "straat_afkomst")),
		@AttributeOverride(name = "plaats", column = @Column(name = "plaats_afkomst")),
		@AttributeOverride(name = "land", column = @Column(name = "land_afkomst"))
	})
	private AdresDataModel afkomst;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "naam", column = @Column(name = "naam_huidige_locatie")),
		@AttributeOverride(name = "postcode", column = @Column(name = "postcode_huidige_locatie")),
		@AttributeOverride(name = "straat", column = @Column(name = "straat_huidige_locatie")),
		@AttributeOverride(name = "plaats", column = @Column(name = "plaats_huidige_locatie")),
		@AttributeOverride(name = "land", column = @Column(name = "land_huidige_locatie"))
	})
	private AdresDataModel huidigeLocatie;
	
	private String soort;
	private boolean spoed;
	private String status;
	private LocalDate aanmaakDatum;
	
	public SorteerItemDataModel(Integer trackId, AdresDataModel doel, AdresDataModel afkomst, AdresDataModel huidigeLocatie, String soort,
			boolean spoed, String status, LocalDate aanmaakDatum) {
		this.trackId=trackId;
		this.doel=doel;
		this.afkomst=afkomst;
		this.huidigeLocatie=huidigeLocatie;
		this.soort=soort;
		this.spoed=spoed;
		this.status=status;
		this.aanmaakDatum=aanmaakDatum;
	}
	
}
