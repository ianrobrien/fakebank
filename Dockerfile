#
# Build stage
#
FROM maven:3.9.0-amazoncorretto-19@sha256:752e30885e1d3cfd0f8e80c4dbe35f0908dee072e117cd64a5294d4433e87698 AS build
COPY . /home/app/
RUN mvn -f /home/app/pom.xml clean install -DskipTests

#
# Package stage
#
FROM amazoncorretto:20-alpine3.16-jdk@sha256:45022c589e2cf0236723f7bc9e2b9341d9621ebecde04448f41ff166fac8765a
COPY --from=build /home/app/fakebank-impl/target/fakebank-impl-1.0-SNAPSHOT.jar /usr/local/lib/fakebank.jar
EXPOSE 8080
ENTRYPOINT java -jar /usr/local/lib/fakebank.jar
