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
	chmod +x mvnw
	./mvnw package -DskipTests
	cd ..
done
