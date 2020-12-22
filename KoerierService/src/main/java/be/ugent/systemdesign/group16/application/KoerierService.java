package be.ugent.systemdesign.group16.application;

import java.time.LocalDate;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Koerier;

public interface KoerierService {
	Response stuurKoerier(Integer orderId, Koerier koerier, Adres ontvanger, Adres afzender, LocalDate aanmaakDatum, boolean spoed, boolean extern, boolean ophalen);
	Response bevestigAfleverenBuren(Integer orderId);
	Response bevestigAfleveren(Integer orderId);
	Response bevestigOphalen(Integer orderId);
}
