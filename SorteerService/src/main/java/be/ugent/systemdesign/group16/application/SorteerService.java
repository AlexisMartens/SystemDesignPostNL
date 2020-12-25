package be.ugent.systemdesign.group16.application;

public interface SorteerService {
	public Response sorteer(Integer sorteerItemId, String naamHuidigeLocatie, String postcodeHuidigeLocatie, String straatHuidigeLocatie,
			String plaatsHuidigeLocatie, String landHuidigeLocatie, String naamDoel, String postcodeDoel, String straatDoel, String plaatsDoel,
			String landDoel, boolean spoed);
}
