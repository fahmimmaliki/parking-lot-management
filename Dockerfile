# Base image for Java
FROM openjdk:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the project files
COPY target/parking-lot-management-0.0.1-SNAPSHOT.jar parkinglot.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "parkinglot.jar"]
