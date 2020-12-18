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
	private Integer zendingId;

	private String typeZending;

	private Adres ontvanger;

	private Adres afzender;
	// afhalen worden? of nog extra attribuut 'boolean afhalen'
	private boolean ophalen;
	
	private Adres huidigeLocatie;

	private LocalDate aanmaakDatum;

	private ZendingStatus status;

	private boolean spoed;

	private boolean extern;

	/*private KoerierService koerierService;
	private MagazijnService magazijnService;
	private SorteerItemManagement sorteerItemManagement;*/
	
	// TODO: constructors

	// TODO: verwerk methode(s)?
}
