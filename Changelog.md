# Changelog

Hieronder is een overzicht terug te vinden van alle gerealiseerde services samen met de naam van de verantwoordelijke.
Er wordt ook telkens aangegeven voor elke service welke eventuele architectuurwijzigingen aangebracht zijn.

## **ZendingManagement** 
`Tristan De Groote`
Dit is de service die volledig werd uitgewerkt.
De systeemoperatie bevestigAfhalen werd weggelaten aangezien deze operatie als redundant kan beschouwd worden: bevestigAfleverenZending wordt ontvangen als event vanuit KoerierService bij ZendingManagement. 'bevestigAfleverenZending' bevestigt namelijk de aflevering van de zending.

Het verwerken van bevestigAfleverenZending werd vereenvoudigd: er wordt met een willekeurige kans bepaald of de zending wordt afgeleverd bij de klant. In het andere geval zal de zending bij een tussenliggend sorteercentrum afgeleverd worden.

## MagazijnService
`Tristan De Groote`
Dit is een stubservice.
## **SorteerItemManagement**
`Toon Mertens`
Dit is de service die volledig werd uitgewerkt.
In de plaats van een document-database als MongoDB, werd de keuze toch gemaakt om met een relationele database te werken. De data is gepast om in een relationeel schema te steken zonder toegevingen te moeten doen in  performantie. 
In het verslag werd aangegeven dat de twee services SorteerService en VervoerService verantwoordelijk zouden zijn voor het uitsturen van UpdateTrackAndTraceDomainEvents. Omdat de SorteerItemManagement een beter overzicht heeft over de locatie van het sorteeritem werd ervoor gekozen om dit in deze service af te handelen. 

## VervoerService
`Toon Mertens`
Dit is een stubservice. Zoals hierboven reeds aangekaart, zal het niet deze service zijn die de UpdateTrackAndTraceDomainEvents verstuurt, maar SorteerItemManagement. 
De systeemoperatie BevestigVervoeren werd niet uitgewerkt aangezien dit een stubservice is.
## SorteerService
`Toon Mertens`
Dit is een stubservice. Ook hier werd het updaten van de trackandtrace niet aangepakt. 
De systeemoperatie BevestigSorteren werd niet uitgewerkt aangezien dit een stubservice is.
## **BestelManagement**
`Alexis Martens`
Dit is de service die volledig werd uitgewerkt.
## ExterneLeveringService
`Alexis Martens`
Dit is een stubservice.
## TrackAndTraceService
`Alexis Martens`
Dit is een stubservice.
## **KoerierService**
`Vincent Van de Sompele`
Dit is de service die volledig werd uitgewerkt.
## FulfilmentBestelManagement
`Vincent Van de Sompele`
Dit is een stubservice.
## KlantenService
`Vincent Van de Sompele`
Dit is een stubservice.
