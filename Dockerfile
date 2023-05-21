FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .
RUN ./mvnw dependency:go-offline -B
COPY src/ src/
RUN ./mvnw package -DskipTests
EXPOSE 9090
CMD ["java", "-jar", "target/electronicTicket-0.0.1-SNAPSHOT.jar"]

