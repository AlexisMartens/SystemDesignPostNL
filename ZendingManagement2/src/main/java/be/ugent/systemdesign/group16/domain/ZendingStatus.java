package be.ugent.systemdesign.group16.domain;

public enum ZendingStatus {AANGEMAAKT, KLAAR_OM_OP_TE_HALEN, OPGEHAALD, AFGELEVERD}
//AANGEMAAKT : er bestaat een zending maar je weet nog niet waar die is
//KLAAR_OM_OP_TE_HALEN: er is een koerier uitgestuurd om pakket op te halen
//OPGEHAALD: reageren op event van koerierservice (enkele status veranderen)
//AFGELEVERD: zending is afgeleverd bij klant thuis (done) OF nieuw sorteeritem event uitsturen 