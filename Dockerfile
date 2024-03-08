FROM openjdk:latest
COPY ./target/Group7CW-0.1-alpha-3.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "Group7CW-0.1-alpha-3.jar"]