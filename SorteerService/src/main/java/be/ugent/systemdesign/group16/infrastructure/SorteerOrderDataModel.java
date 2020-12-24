package be.ugent.systemdesign.group16.infrastructure;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SorteerOrderDataModel {
	private Integer sorteerItemId;
	private Integer batchId;
	private boolean spoed;
	private boolean eindBestemming;
	private String naamDoel, postcodeDoel, straatDoel, plaatsDoel, landDoel;
}
