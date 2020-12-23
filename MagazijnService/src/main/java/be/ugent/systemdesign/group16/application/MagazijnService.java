package be.ugent.systemdesign.group16.application;

import java.time.LocalDate;


public interface MagazijnService {
	//TODO: args!
	Response MaakPakket(Integer _pakketId, String _naamOntvanger, String _postcodeOntvanger, String _straatOntvanger, String _plaatsOntvanger, String _landOntvanger, 
			String _naamAfzender, String _postcodeAfzender, String _straatAfzender, String _plaatsAfzender, String _landAfzender,
			String _naamHuidigeLocatie, String _postcodeHuidigeLocatie, String _straatHuidigeLocatie, String _plaatsHuidigeLocatie, String _landHuidigeLocatie,
			String soort, boolean spoed, String status);

	Response BevestigInpakken(Integer _pakketId);
}
