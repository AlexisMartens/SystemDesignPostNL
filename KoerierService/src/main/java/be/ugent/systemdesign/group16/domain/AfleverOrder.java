package be.ugent.systemdesign.group16.domain;

import java.time.LocalDate;

public class AfleverOrder extends Order{

	public AfleverOrder(Integer orderId, Adres ontvanger, Adres afzender, boolean ophalen, LocalDate aanmaakDatum,
			boolean spoed, boolean extern) {
		super(orderId, ontvanger, afzender, ophalen, aanmaakDatum, spoed, extern);
		// TODO Auto-generated constructor stub
		
	}
	
	
}
