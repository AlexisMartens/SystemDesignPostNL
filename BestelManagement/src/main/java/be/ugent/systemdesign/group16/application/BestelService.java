package be.ugent.systemdesign.group16.application;

public interface BestelService {
	Response plaatsBestelling(String _typeBestelling, String _naamOntvanger, String _postcodeOntvanger, String _straatOntvanger, String _plaatsOntvanger, String _landOntvanger, String _naamAfzender, String _postcodeAfzender, String _straatAfzender, String _plaatsAfzender, String _landAfzender, boolean _spoed, boolean _extern, String _externeLeveringService);
	Response plaatsRetour(Integer _bestellingId);
}
