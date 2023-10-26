# Use a base image with Java (e.g., OpenJDK)
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY target/pdt_sales-0.0.1-SNAPSHOT.jar app.jar

# Set the environment variable for the port
ENV PORT=8080

# Expose the port that your Spring Boot application listens on (default is 8080)
EXPOSE $PORT

# Define the command to run your Spring Boot application
CMD  ["java", "-jar", "app.jar"]

