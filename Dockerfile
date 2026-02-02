# Use OpenJDK 17 slim image as base
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the Maven wrapper and pom.xml first for better caching
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

# Download dependencies (caching layer)
RUN chmod +x ./mvnw && \
    ./mvnw dependency:go-offline -B

# Copy the rest of the application source
COPY src src

# Build the application
RUN chmod +x ./mvnw && \
    ./mvnw clean package -DskipTests -B

# Expose the default Spring Boot port
EXPOSE 8080

# Set the entrypoint command to run the application
ENTRYPOINT ["java", "-jar", "target/kanban-task-manager-0.0.1-SNAPSHOT.jar"]