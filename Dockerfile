#
# Build stage
#
FROM maven:3.9.0-amazoncorretto-19@sha256:533efe3b641ca9d17956ce117d0e04c1dff57dda108d3ec830152e0fad5e95ea AS build
COPY . /home/app/
RUN mvn -f /home/app/pom.xml clean install -DskipTests

#
# Package stage
#
FROM amazoncorretto:19-alpine3.16-jdk@sha256:7949de351f5378500ded7e0422f761086c8d290e51e6d015a789ff54317b6fcb
COPY --from=build /home/app/fakebank-impl/target/fakebank-impl-1.0-SNAPSHOT.jar /usr/local/lib/fakebank.jar
EXPOSE 8080
ENTRYPOINT java -jar /usr/local/lib/fakebank.jar
