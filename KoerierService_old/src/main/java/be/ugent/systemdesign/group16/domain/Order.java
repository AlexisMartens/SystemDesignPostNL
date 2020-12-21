package be.ugent.systemdesign.group16.domain;

import java.time.LocalDate;

public abstract class Order {
	private Integer orderId;
	
	private Adres ontvanger;
	
	private Adres afzender;
	
	private boolean ophalen;
	
	private LocalDate aanmaakDatum;
	
	private boolean spoed;
	
	private boolean extern;

	public Order(Integer orderId, Adres ontvanger, Adres afzender, boolean ophalen, LocalDate aanmaakDatum,
			boolean spoed, boolean extern) {
		super();
		this.orderId = orderId;
		this.ontvanger = ontvanger;
		this.afzender = afzender;
		this.ophalen = ophalen;
		this.aanmaakDatum = aanmaakDatum;
		this.spoed = spoed;
		this.extern = extern;
	}
	
	public String getPostCode(){
		return 
	}
	
	
}
