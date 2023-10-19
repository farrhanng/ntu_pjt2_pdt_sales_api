# Use a base image with Java (e.g., OpenJDK)
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY target/pdt_sales-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your Spring Boot application listens on (default is 8080)
EXPOSE 8080

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]
# could be pdt_sales-0.0.1-SNAPSHOT.jar 
# target/pdt_sales-0.0.1-SNAPSHOT.jar
