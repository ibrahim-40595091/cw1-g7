FROM openjdk:latest

# Copy the JAR file from the local filesystem to the container
COPY target/G7CW.jar /tmp

# Set the working directory in the container
WORKDIR /tmp

# Define the entry point for running the application
ENTRYPOINT ["java", "-jar", "G7CW.jar", "localhost:33060", "10000"]
