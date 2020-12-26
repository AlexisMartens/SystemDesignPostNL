package be.ugent.systemdesign.group16.application;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Zending;

public interface ZendingService {
	Response bevestigAankomstNieuweZending(Integer _zendingId, Adres adresAfhaalpunt);
	Response bevestigAfhalen(Integer _zendingId);

	Response maakNieuweZending(String _typeZending, String _naamOntvanger, String _postcodeOntvanger, String _straatOntvanger, String _plaatsOntvanger, String _landOntvanger, String _naamAfzender, String _postcodeAfzender, String _straatAfzender, String _plaatsAfzender, String _landAfzender, boolean _ophalenBijKlant, boolean _spoed);
	Response maakNieuweZending(Zending _z);
	Response bevestigAfleverenZending(Integer _zendingId, Adres _huidigeLocatie);
	Response bevestigOphalenZending(Integer _zendingId);
}
