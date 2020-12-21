package be.ugent.systemdesign.group16.application;

import be.ugent.systemdesign.group16.domain.Zending;

public interface ZendingService {
	Response bevestigAankomstNieuweZending(Zending _z);
	Response bevestigAfhalen(Integer _zendingId);
}
