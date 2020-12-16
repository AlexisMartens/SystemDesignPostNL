package be.ugent.systemdesign.group16.application;

import be.ugent.systemdesign.group16.domain.Bestelling;

public interface BestelService {
	Response plaatsBestelling(Bestelling _b);
	Response plaatsRetour(Integer _bestellingId);
}
