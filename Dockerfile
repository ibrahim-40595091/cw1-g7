FROM openjdk:latest
COPY target/Group7CW-0.1.0.2.jar /app/app.jar
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "/app/app.jar"]