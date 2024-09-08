# This Dockerfile builds a Java application using OpenJDK 17 and Alpine Linux
# It sets up a user and group named "spring" and copies the application's dependencies and classes into the container
# The ENTRYPOINT command runs the Java application with the classpath set to the app directory and its subdirectories

FROM openjdk:17-jdk-alpine

# Create a new group and user named "spring"
RUN addgroup -S spring && adduser -S spring -G spring

# Set the default user and group for the Docker container to "spring"
USER spring:spring

# Define the dependency JAR file
ARG DEPENDENCY=target/dependency

# Copy the dependency JAR file's lib directory to the /app/lib directory
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib

# Copy the dependency JAR file's META-INF directory to the /app/META-INF directory
COPY ${DEPENDENCY}/META-INF /app/META-INF

# Copy the dependency JAR file's classes directory to the /app directory
COPY ${DEPENDENCY}/BOOT-INF/classes /app

# Set the default command to run when the Docker container starts
ENTRYPOINT ["java","-cp","app:app/lib/*","com.fahmi.parking_lot_management.ParkingLotManagementApplication"]