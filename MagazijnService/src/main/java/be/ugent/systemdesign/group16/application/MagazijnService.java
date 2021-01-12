package be.ugent.systemdesign.group16.application;

import java.time.LocalDate;

import be.ugent.systemdesign.group16.domain.Pakket;
import be.ugent.systemdesign.group16.domain.UpdateTrackAndTraceDomainEvent;


public interface MagazijnService {
	
	Response maakPakket(Pakket _p);

	Response MaakPakket(Integer _pakketId, 
			String _naamOntvanger, String _postcodeOntvanger, String _straatOntvanger, String _plaatsOntvanger, String _landOntvanger, 
			String _naamAfzender, String _postcodeAfzender, String _straatAfzender, String _plaatsAfzender, String _landAfzender,
			String soort, boolean _ophalenBijKlant, boolean _spoed);

	Response BevestigInpakken(Integer _pakketId);
	
	Response UpdateTrackAndTrace(UpdateTrackAndTraceDomainEvent e);

}
