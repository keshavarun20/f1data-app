FROM eclipse-temurin:17-jdk-jammy as builder

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw

# Download dependencies only (no repackage attempted, no src needed)
RUN ./mvnw dependency:go-offline -B

# NOW copy source
COPY src /app/src

# Single build step — package produces the fat JAR
RUN ./mvnw package -DskipTests

FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=builder /app/target/f1dataapp-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
