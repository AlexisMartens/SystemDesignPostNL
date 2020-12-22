package be.ugent.systemdesign.group16.domain;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SorteerItem {
	
	private Integer sorteerItemId;
	private Adres doel, afkomst;
	private Soort soort;
	private Adres huidigeLocatie;
	private boolean isBijLaatsteLocatie;
	private boolean spoed;
	private LocalTime aanmaakDatum;
	
	public void aangekomenOpNieuweLocatie(Adres l) {
		this.huidigeLocatie = l;
	}
	
	public void aangekomenOpLaatsteLocatie(Adres l) {
		this.huidigeLocatie = l;
		// Maak nieuwe zending aan
	}
	
	
}