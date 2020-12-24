package be.ugent.systemdesign.group16.application;

public interface VervoerService {

	Response vervoer(Integer sorteerItemId, 
			String naamVan, String postcodeVan, String straatVan, String plaatsVan, String landVan,
			String naamNaar, String postcodeNaar, String straatNaar, String plaatsNaar, String landNaar,
			Integer batchId, boolean spoed);
}
