package be.ugent.systemdesign.group16.application.event;

import java.time.LocalDate;

import be.ugent.systemdesign.group16.domain.Adres;
import be.ugent.systemdesign.group16.domain.Koerier;
import be.ugent.systemdesign.group16.domain.OrderStatus;
import be.ugent.systemdesign.group16.domain.seedwork.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StuurKoerierDomainEvent extends DomainEvent {
    private Integer zendingId;
    
    private String typeZending;
        
    private String naamVan;
    private String postcodeVan;
    private String straatVan;
    private String plaatsVan;
    private String landVan;
    
    private String naamNaar;
    private String postcodeNaar;
    private String straatNaar;
    private String plaatsNaar;
    private String landNaar;
    
    private boolean spoed;

 

}
