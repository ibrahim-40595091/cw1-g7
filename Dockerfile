FROM openjdk:latest

# Update version number here - automatically builds with the correct filenames
# Remember to update Maven version number in pom.xml
ENV VERSION=0.1-alpha-3

COPY target/Group7CW-${VERSION}-jar-with-dependencies.jar /tmp/
WORKDIR /tmp
ENTRYPOINT java -jar "Group7CW-${VERSION}-jar-with-dependencies.jar"