FROM openjdk:latest

# Copy the JAR file from the local filesystem to the container
COPY target/G7CW.jar /tmp

# Set the working directory in the container
WORKDIR /tmp

# Define the entry point for running the application
ENTRYPOINT ["java", "-jar", "G7CW.jar", "cw1-g7_sql_db_1:3306", "10000"]
