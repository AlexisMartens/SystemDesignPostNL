package be.ugent.systemdesign.group16.domain;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor()
public class Koerier {
	
	private Integer koerierId;
	
	private String naam;
	
	private String postcodeRonde;
	
	private Integer vervoercapaciteit;
	
    private List<Order> orders;
    
	public Koerier(Integer koerierId, String naam, String postcodeRonde, Integer vervoercapaciteit) {
		this.koerierId = koerierId;
		this.naam = naam;
		this.postcodeRonde = postcodeRonde;
		this.vervoercapaciteit = vervoercapaciteit;
		this.orders = new ArrayList<>();
	}
	
	public void VoegOrderToe(Order order){
		if(order.getAfzender().getPostcode().equals(this.postcodeRonde) || order.getOntvanger().getPostcode().equals(this.postcodeRonde)) {
				orders.add(order);
		}
		else {
			throw new NietInRondeException();
		}
		
		
	}
}