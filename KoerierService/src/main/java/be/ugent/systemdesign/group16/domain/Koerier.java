package be.ugent.systemdesign.group16.domain;


import java.time.LocalDate;
import java.util.ArrayList;

import be.ugent.systemdesign.group16.domain.seedwork.AggregateRoot;
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
public class Koerier extends AggregateRoot {
	
	private Integer KoerierId;
	
	private String naam;
		
	private ArrayList<AfleverOrder> afleverOrders;
	
	private ArrayList<OphaalOrder> ophaalOrders;
	
	private boolean beschikbaarVoorMeerOrders;
	
	private String postcodeRegio;
	
	public Koerier(String _naam, String _postcodeRegio, boolean _beschikbaarVoorMeerOrders) {
		naam = _naam;
		postcodeRegio = _postcodeRegio;
		beschikbaarVoorMeerOrders = _beschikbaarVoorMeerOrders;
		afleverOrders = new ArrayList<>();
		ophaalOrders = new ArrayList<>();
	}
	
	public void VoegAfleverOrderToe(AfleverOrder afleverOrder){
		if(beschikbaarVoorMeerOrders && postcodeRegio == afleverOrder.getOntvanger().getPostCode()) {
			afleverOrders.add(afleverOrder);
		}
		else {
			//exception
		}
	}
	
	public void VoegOphaalOrderToe(OphaalOrder ophaalOrder){
		if(beschikbaarVoorMeerOrders && postcodeRegio == ophaalOrder.getAfzender().getPostCode()) {
			ophaalOrders.add(ophaalOrder);
		}
		else{
			//exception
		}
	}
}