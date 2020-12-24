package be.ugent.systemdesign.group16.infrastructure;

import javax.persistence.Embeddable;

import be.ugent.systemdesign.group16.domain.Adres;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VervoerOrderDataModel {
	private Integer sorteerItemId;
	private Integer batchId;
	
	private String naamVan;
	private String postcodeVan;
	private String straatVan;
	private String plaatsVan;
	private String landVan;
	
	private String naamNaar;
	private String postcodeNaar;
	private String straatNaar;
	private String plaatsNaar;
	private String landNaar;
}
