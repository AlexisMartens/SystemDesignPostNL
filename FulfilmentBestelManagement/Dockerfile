FROM openjdk:11
COPY . .
RUN apt-get install -y curl && ./mvnw package -DskipTests
ENTRYPOINT ["java", "-jar", "./target/FulfilmentBestelManagement-0.0.1-SNAPSHOT.jar"]
