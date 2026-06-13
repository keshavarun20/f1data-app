# Use Eclipse Temurin (official OpenJDK successor) as builder
FROM eclipse-temurin:17-jdk-jammy as builder

# Set working directory
WORKDIR /app

# Copy the pom.xml and mvnw files to the container
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Give execute permission to mvnw
RUN chmod +x mvnw

# Run the Maven wrapper to build the application
RUN ./mvnw clean install -DskipTests

# Copy the application source code to the container
COPY src /app/src

# Build the application
RUN ./mvnw package -DskipTests

# Use JRE (not JDK) for the runtime — lighter
FROM eclipse-temurin:17-jre-jammy

# Set working directory
WORKDIR /app

# Copy the built JAR from the builder image
COPY --from=builder /app/target/f1dataapp-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port the app will run on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
