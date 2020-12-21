package be.ugent.systemdesign.group16.API.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.Gateway;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.group16.application.event.EventHandler;
import be.ugent.systemdesign.group16.application.event.ZendingDomainEvent;

@Component
public class MessageInputGateway {
	@Autowired
	EventHandler eventHandler;
	@Autowired
	Channels channels;
	
	@StreamListener(Channels.ZENDING_EVENT)
	public void consumeNieuweZendingEvent(ZendingDomainEvent event) {
		eventHandler.handleNieuweZending(event);
	}
	//TODO: onderstaande nog verwerken:
	
	
	/*@StreamListener(Channels.ZENDING_EVENT_SORTEERITEM_MGMT)
	public void consumeNieuweZendingVanSorteerItemMgmtEvent(NieuweZendingVanSorteerItemMgmt event) {
		eventHandler.handleNieuweZendingVanSorteerItemMgmtEvent(event);
	}*/
	/*@StreamListener(Channels.OPHALEN_ZENDING_EVENT)
	public void consumeOphalenZendingEvent(OphalenZendingDomainEvent event) {
		eventHandler.handleOphalenZending(event);
	}*/
	/*
	@StreamListener(Channels.AFLEVEREN_ZENDING_EVENT)
	public void consumeAfleverenZendingEvent(AfleverenZendingDomainEvent event) {
		eventHandler.handleAfleverenNieuweZending(event);
	}*/
}
