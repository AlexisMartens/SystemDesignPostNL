package be.ugent.systemdesign.group16.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VervoerOrder {

	private Integer sorteerItemId;
	private Integer batchId;
	private Adres van;
	private Adres naar;
}
