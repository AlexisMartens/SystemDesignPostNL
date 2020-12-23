package be.ugent.systemdesign.group16.domain;

import java.time.LocalDate;

import be.ugent.systemdesign.group16.domain.seedwork.AggregateRoot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SorteerItem extends AggregateRoot{
	
	private Integer sorteerItemId;
	private Integer trackId;
	private Adres doel;
	private Adres afkomst;
	private Adres huidigeLocatie;
	private Soort soort;
	private boolean spoed;
	private SorteerItemStatus status;
	private LocalDate aanmaakDatum;
	
	public SorteerItem(Integer trackId, Adres doel, Adres afkomst, Adres huidigeLocatie,
			Soort soort, boolean spoed, LocalDate aanmaakDatum) {
		this.trackId = trackId;
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
		// Stuur Sorteerder
	}
	
	public void maakKlaarVoorVervoer(Adres volgendeLocatie, boolean laatsteLocatie, Integer batchId) {
		this.status = laatsteLocatie ? SorteerItemStatus.ONDERWEG_NAAR_LAATSTE_LOCATIE : SorteerItemStatus.ONDERWEG;
		updateTrackAndTrace();
		addDomainEvent(new StuurVervoerderDomainEvent(this.sorteerItemId, this.huidigeLocatie, volgendeLocatie, batchId, this.spoed));
	}
	
	public void aangekomenOpLaatsteLocatie(Adres l) {
		this.huidigeLocatie = l;
		this.status = SorteerItemStatus.KLAAR_VOOR_ZENDING;
		// Maak nieuwe zending aan
	}
	
	private void updateTrackAndTrace() {
		addDomainEvent(new UpdateTrackAndTraceDomainEvent(this));
	}
}