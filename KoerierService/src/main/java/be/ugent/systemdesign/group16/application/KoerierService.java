package be.ugent.systemdesign.group16.application;

import be.ugent.systemdesign.group16.domain.Order;

public interface KoerierService {
	Response stuurKoerier(Integer orderId);
	Response bevestigAfleverenBuren(Integer orderId);
	Response bevestigAfleveren(Integer orderId);
	Response bevestigOphalen(Integer orderId);
}
