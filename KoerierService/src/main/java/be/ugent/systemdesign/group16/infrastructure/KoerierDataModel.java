package be.ugent.systemdesign.group16.infrastructure;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class KoerierDataModel {
	@Id
	@Getter
	private Integer koerierId;
	
	private String naam;
	
	private String postcodeRonde;
	
	private Integer vervoercapaciteit;
	
	@OneToMany(mappedBy = "koerierDataModel")
    private List<OrderDataModel> orders;

	public KoerierDataModel(Integer koerierId, String naam, String postcodeRonde, Integer vervoercapaciteit) {
		this.koerierId = koerierId;
		this.naam = naam;
		this.postcodeRonde = postcodeRonde;
		this.vervoercapaciteit = vervoercapaciteit;
		this.orders = new ArrayList<>();
	}
	
}
