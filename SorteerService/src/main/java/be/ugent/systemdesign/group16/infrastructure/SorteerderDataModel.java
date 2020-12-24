package be.ugent.systemdesign.group16.infrastructure;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SorteerderDataModel {

	@Id
	@GeneratedValue
	private Integer sorteerderId;
	
	private String naam;
	private String status;
	
	@Embedded
	private SorteerOrderDataModel order;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "locatie_id")
	private SorteerCentrumDataModel werkLocatie;
}
