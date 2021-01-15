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

## VervoerService
`Toon Mertens`

## SorteerService
`Toon Mertens`

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

