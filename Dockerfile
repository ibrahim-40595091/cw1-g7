FROM openjdk:latest

# Copy the JAR file from the local filesystem to the container
COPY target/Group7CW-0.1.0.2-jar-with-dependencies.jar /app/

# Set the working directory in the container
WORKDIR /app

# Define the entry point for running the application
ENTRYPOINT ["java", "-jar", "Group7CW-0.1.0.2-jar-with-dependencies.jar"]
