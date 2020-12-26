FROM openjdk:11
COPY . .
RUN ./mvnw package -DskipTests
CMD ["java","-jar","target/SorteerItemManagement-0.0.1-SNAPSHOT.jar"]