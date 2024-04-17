FROM openjdk:latest

# Copy the JAR file from the local filesystem to the container
COPY target/G7CW.jar /app/

# Set the working directory in the container
WORKDIR /app

# Define the entry point for running the application
ENTRYPOINT ["java", "-jar", "G7CW.jar", "sql_db:3306", "10000"]
