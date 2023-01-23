#
# Build stage
#
FROM maven:3.8.7-amazoncorretto-19@sha256:49da3aabc69c9fa831b4b4bfd1c83814ecadc3aa5a2061900b5a8c02a4d560ce AS build
COPY . /home/app/
RUN mvn -f /home/app/pom.xml clean install -DskipTests

#
# Package stage
#
FROM amazoncorretto:19-alpine3.16-jdk@sha256:3420f2939e52769b1b9996be2930b7bca1463c50d262987b1e4b65f4de12352f
COPY --from=build /home/app/fakebank-impl/target/fakebank-impl-1.0-SNAPSHOT.jar /usr/local/lib/fakebank.jar
EXPOSE 8080
ENTRYPOINT java -jar /usr/local/lib/fakebank.jar
