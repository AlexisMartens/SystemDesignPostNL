package be.ugent.systemdesign.group16.domain;

import java.time.LocalDate;

import be.ugent.systemdesign.group16.domain.seedwork.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SorteerItem extends AggregateRoot{
	
	private Integer sorteerItemId;
	private Adres doel;
	private Adres afkomst;
	private Adres huidigeLocatie;
	private Soort soort;
	private boolean spoed;
	private SorteerItemStatus status;
	private LocalDate aanmaakDatum;
	
	public SorteerItem(Integer sorteerItemId, Adres doel, Adres afkomst, Adres huidigeLocatie,
			Soort soort, boolean spoed, LocalDate aanmaakDatum) {
		this.sorteerItemId = sorteerItemId;
		this.doel = doel;
		this.afkomst = afkomst;
		this.huidigeLocatie = huidigeLocatie;
		this.soort = soort;
		this.spoed = spoed;
		this.status = SorteerItemStatus.IN_CENTRUM;
		this.aanmaakDatum = aanmaakDatum;
	}
	
	public void aangekomenOpNieuweLocatie(Adres l) {
		this.huidigeLocatie = l;
		this.status = SorteerItemStatus.IN_CENTRUM;
	}
	
	public void maakKlaarVoorVervoer() {
		if(this.status == SorteerItemStatus.KLAAR_VOOR_ZENDING) {
			throw new SorteerItemAlGesorteerdException();
		}
		addDomainEvent(new StuurVervoerderEvent());
		this.status = SorteerItemStatus.ONDERWEG;
	}
	
	public void aangekomenOpLaatsteLocatie(Adres l) {
		this.huidigeLocatie = l;
		this.status = SorteerItemStatus.KLAAR_VOOR_ZENDING;
		// Maak nieuwe zending aan
	}
	
	
}