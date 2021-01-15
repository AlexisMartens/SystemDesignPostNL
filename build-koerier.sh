#! /bin/bash

declare -a services=(
	"KoerierService"
	"APIGateway"
)

for s in "${services[@]}"
do
	cd $s
	chmod +x mvnw
	./mvnw package -DskipTests
	cd ..
done
