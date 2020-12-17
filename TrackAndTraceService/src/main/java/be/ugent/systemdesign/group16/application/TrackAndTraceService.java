package be.ugent.systemdesign.group16.application;

import be.ugent.systemdesign.group16.domain.TrackAndTrace;

public interface TrackAndTraceService {
	Response MaakTrackAndTrace(Integer bestellingId, String status);
	Response UpdateTrackAndTrace(Integer bestellingId, String naam, String postcode, String straat, String plaats, String land, String status);
	TrackAndTrace GetTrackAndTrace(Integer bestellingId);
}
