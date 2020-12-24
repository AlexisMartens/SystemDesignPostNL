package be.ugent.systemdesign.group16.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SorteerOrder {
	private Integer sorteerItemId;
	private Adres doel;
	private boolean spoed;
	private Integer batchId;
	private boolean eindBestemming;
}
