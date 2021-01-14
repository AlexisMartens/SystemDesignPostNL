#! /bin/bash

declare -a services=(
	"BestelManagement"
	"ExterneLeveringService"
	"FulfilmentBestelManagement"
	"KlantenService"
	"KoerierService"
	"MagazijnService"
	"SorteerItemManagement"
	"SorteerService"
	"TrackAndTraceService"
	"VervoerService"
	"ZendingManagement2"
	"APIGateway"
)

for s in "${services[@]}"
do
	cd $s
	./mvnw clean
	cd ..
done
