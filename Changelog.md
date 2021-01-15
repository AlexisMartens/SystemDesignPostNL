# Changelog

Hieronder is een overzicht terug te vinden van alle gerealiseerde services samen met de naam van de verantwoordelijke.
Er wordt ook telkens aangegeven voor elke service welke eventuele architectuurwijzigingen aangebracht zijn.

Met een pijltje ">" wordt aangegeven of de microservice een 'hoofdimplementatie' is.

## >**ZendingManagement** 
`Tristan De Groote`
De systeemoperatie 'bevestigAfhalen' werd weggelaten aangezien deze operatie als redundant kan beschouwd worden: 'bevestigAfleverenZending' wordt ontvangen als event vanuit KoerierService bij ZendingManagement. 'bevestigAfleverenZending' bevestigt namelijk de aflevering van de zending.
## MagazijnService
`Tristan De Groote`

## >**SorteerItemManagement**
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
## >**BestelManagement**
`Alexis Martens`

## ExterneLeveringService
`Alexis Martens`

## TrackAndTraceService
`Alexis Martens`

## >**KoerierService**
`Vincent Van de Sompele`

## FulfilmentBestelManagement
`Vincent Van de Sompele`

## KlantenService
`Vincent Van de Sompele`

