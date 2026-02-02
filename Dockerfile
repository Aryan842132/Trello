# Use Eclipse Temurin (Adoptium) Java 17 image as base
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Install Maven (Alpine doesn't have it by default)
RUN apk add --no-cache maven

# Copy pom.xml first for better caching
COPY pom.xml .

# Download dependencies (caching layer)
RUN mvn dependency:go-offline -B

# Copy the rest of the application source
COPY src src

# Build the application
RUN mvn clean package -DskipTests -B

# Expose the default Spring Boot port
EXPOSE 8080

# Set the entrypoint command to run the application
ENTRYPOINT ["java", "-jar", "target/kanban-task-manager-0.0.1-SNAPSHOT.jar"]