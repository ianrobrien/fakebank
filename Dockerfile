#
# Build stage
#
FROM maven:3.9.1-amazoncorretto-19@sha256:f03397d19ff99a9348bcd29b42eacd96cba06bc88ccaf6acf1603706e8dd80e1 AS build
COPY . /home/app/
RUN mvn -f /home/app/pom.xml clean install -DskipTests

#
# Package stage
#
FROM amazoncorretto:20-alpine3.16-jdk@sha256:45022c589e2cf0236723f7bc9e2b9341d9621ebecde04448f41ff166fac8765a
COPY --from=build /home/app/fakebank-impl/target/fakebank-impl-1.0-SNAPSHOT.jar /usr/local/lib/fakebank.jar
EXPOSE 8080
ENTRYPOINT java -jar /usr/local/lib/fakebank.jar
