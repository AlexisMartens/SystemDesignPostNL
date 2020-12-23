package be.ugent.systemdesign.group16.application;

import java.time.LocalDate;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Koerier;

public interface KoerierService {
	Response stuurKoerier(Integer orderId, String naamAfzender, String postcodeAfzender, String straatAfzender,
			String plaatsAfzender, String landAfzender, String naamOntvanger, String postcodeOntvanger,
			String straatOntvanger, String plaatsOntvanger, String landOntvanger, boolean spoed, boolean extern,
			boolean ophalen);

	Response bevestigAfleverenBuren(Integer orderId);

	Response bevestigAfleveren(Integer orderId);

	Response bevestigOphalen(Integer orderId);
}
