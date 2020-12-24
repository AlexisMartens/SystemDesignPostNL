package be.ugent.systemdesign.group16.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SorteerBestemming {

	private Locatie volgendeLocatie;
	private boolean eindBestemming;
	private Integer batchId;
}
