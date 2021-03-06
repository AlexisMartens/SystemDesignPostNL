package be.ugent.systemdesign.group16.infrastructure;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import be.ugent.systemdesign.group16.domain.VervoerStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VervoerderDataModel {
	
	@Id
	@Getter
	@GeneratedValue
	private Integer vervoerderId;
	
	private String naam;
	private String status;
	
	@Embedded 
	private VervoerOrderDataModel order;
	
	public VervoerderDataModel(String naam, String status) {
		this.naam=naam;
		this.status=status;
		this.order=new VervoerOrderDataModel();
	}
}