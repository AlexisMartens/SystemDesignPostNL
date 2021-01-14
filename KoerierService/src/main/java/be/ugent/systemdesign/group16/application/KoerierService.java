package be.ugent.systemdesign.group16.application;

import java.time.LocalDate;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Koerier;

public interface KoerierService {
	Response stuurKoerier(Integer zendingId, String typeZending, String naamVan, String postcodeVan,
			String straatVan, String plaatsVan, String landVan, String naamNaar,
			String postcodeNaar, String straatNaar, String plaatsNaar, String landNaar);

	Response bevestigAfleverenBuren(Integer orderId);

	Response bevestigAfleveren(Integer orderId);

	Response bevestigOphalen(Integer orderId);
}
