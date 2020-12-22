package be.ugent.systemdesign.group16.application;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Zending;

public interface ZendingService {
	Response bevestigAankomstNieuweZending(Integer _zendingId, Adres adresAfhaalpunt);
	Response bevestigAfhalen(Integer _zendingId);
	//TODO: nog voor consume events
}
