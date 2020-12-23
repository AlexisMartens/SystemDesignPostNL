package be.ugent.systemdesign.group16.application;

import java.time.LocalDate;

import be.ugent.systemdesign.group16.domain.SorteerItem;

public interface SorteerItemService {

	Response maakNieuwSorteerItem(SorteerItem _s);
	
	Response maakNieuwSorteerItem(Integer trackId, String naamDoel,
			String postcodeDoel, String straatDoel, String plaatsDoel, String landDoel, String naamAfkomst, String postcodeAfkomst,
			String straatAfkomst, String plaatsAfkomst, String landAfkomst, String naamHuidigeLocatie, String postcodeHuidigeLocatie,
			String straatHuidigeLocatie, String plaatsHuidigeLocatie, String landHuidigeLocatie, String soort, boolean spoed, LocalDate aanmaakDatum);
	
	Response gesorteerd(Integer sorteerItemId, String naamVolgendeLocatie, String postcodeVolgendeLocatie, String straatVolgendeLocatie,
			String plaatsVolgendeLocatie, String landVolgendeLocatie, boolean laatsteLocatie, Integer batchId);
	
	Response vervoerd(Integer sorteerItemId, String naamNieuweLocatie, String postcodeNieuweLocatie, String straatNieuweLocatie,
			String plaatsNieuweLocatie, String landNieuweLocatie);
}