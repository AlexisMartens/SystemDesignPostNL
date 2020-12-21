package be.ugent.systemdesign.group16.infrastructure;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderDataModel {
	
	@Id
	private Integer orderId;
	
	@ManyToOne(targetEntity = KoerierDataModel.class)
	@JoinColumn(name = "koerier_id", nullable = false)
	private KoerierDataModel koerierDataModel;
		
	private String naamAfzender;
	private String postcodeAfzender;
	private String straatAfzender;
	private String plaatsAfzender;
	private String landAfzender;
	
	private String naamOntvanger;
	private String postcodeOntvanger;
	private String straatOntvanger;
	private String plaatsOntvanger;
	private String landOntvanger;
	
	private LocalDate aanmaakDatum;
	
	private boolean spoed;
	
	private boolean extern;
		
	private String orderStatus;

	public OrderDataModel(Integer orderId, KoerierDataModel koerierDataModel, String naamAfzender,
			String postcodeAfzender, String straatAfzender, String plaatsAfzender, String landAfzender,
			String naamOntvanger, String postcodeOntvanger, String straatOntvanger, String plaatsOntvanger,
			String landOntvanger, LocalDate aanmaakDatum, boolean spoed, boolean extern,
			String orderStatus) {
		this.orderId = orderId;
		this.koerierDataModel = koerierDataModel;
		this.naamAfzender = naamAfzender;
		this.postcodeAfzender = postcodeAfzender;
		this.straatAfzender = straatAfzender;
		this.plaatsAfzender = plaatsAfzender;
		this.landAfzender = landAfzender;
		this.naamOntvanger = naamOntvanger;
		this.postcodeOntvanger = postcodeOntvanger;
		this.straatOntvanger = straatOntvanger;
		this.plaatsOntvanger = plaatsOntvanger;
		this.landOntvanger = landOntvanger;
		this.aanmaakDatum = aanmaakDatum;
		this.spoed = spoed;
		this.extern = extern;
		this.orderStatus = orderStatus;
	}


	}


	
	
