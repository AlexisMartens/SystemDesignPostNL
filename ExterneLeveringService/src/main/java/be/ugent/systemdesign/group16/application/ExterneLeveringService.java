package be.ugent.systemdesign.group16.application;

public interface ExterneLeveringService {
	Response MaakBestelling(Integer _bestellingId, String _typeBestelling, String _naamOntvanger, String _postcodeOntvanger, String _straatOntvanger, String _plaatsOntvanger, String _landOntvanger, String _naamAfzender, String _postcodeAfzender, String _straatAfzender, String _plaatsAfzender, String _landAfzender, boolean _ophalen, String _status, boolean _spoed, boolean _extern, String _externeLeveringService);
	Response UpdateTrackAndTrace(Integer _bestellingId);
}
