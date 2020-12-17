package be.ugent.systemdesign.group16.application;

import be.ugent.systemdesign.group16.domain.Zending;

public interface ZendingService {
	Response plaatsZending(Zending _z);
	// retour plaatsen nodig?
	Response plaatsRetour(Integer _zendingId);
}
