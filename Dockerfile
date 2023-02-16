#
# Build stage
#
FROM maven:3.9.0-amazoncorretto-19@sha256:88ef10673d5fc9071a0b4dad528766e1eeac0fe7756b51ed8dffb4daa8486904 AS build
COPY . /home/app/
RUN mvn -f /home/app/pom.xml clean install -DskipTests

#
# Package stage
#
FROM amazoncorretto:19-alpine3.16-jdk@sha256:7949de351f5378500ded7e0422f761086c8d290e51e6d015a789ff54317b6fcb
COPY --from=build /home/app/fakebank-impl/target/fakebank-impl-1.0-SNAPSHOT.jar /usr/local/lib/fakebank.jar
EXPOSE 8080
ENTRYPOINT java -jar /usr/local/lib/fakebank.jar
