FROM openjdk:11
COPY . .
RUN ./mvnw package -DskipTests
ENTRYPOINT ["java","-jar","target/VervoerService-0.0.1-SNAPSHOT.jar"]
