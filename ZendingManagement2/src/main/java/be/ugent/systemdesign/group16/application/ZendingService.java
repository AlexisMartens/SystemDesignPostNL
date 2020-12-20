package be.ugent.systemdesign.group16.application;

import be.ugent.systemdesign.group16.domain.Zending;

public interface ZendingService {
	Response bevestigAankomstNieuweZending(Zending _z, String _huidigeLocatieNaam, String _huidigePostcode, String _huidigeStraat, String huidigePlaats, String _huidigLand);
	Response bevestigAfhalen(Integer _zendingId);
}
