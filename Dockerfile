#
# Build stage
#
FROM maven:3.9.2-eclipse-temurin-20-alpine@sha256:7b255e62e09561d2f91f86547ab897a245d3a120b3caed2049cb0eaa5fe79dab AS build
COPY . /home/app/
RUN mvn -f /home/app/pom.xml clean install -DskipTests

#
# Package stage
#
FROM eclipse-temurin:20-alpine@sha256:caaf2247655613ab70bac6a5b927d85a6df223c8697c73c18cba804f297048d3
COPY --from=build /home/app/fakebank-impl/target/fakebank-impl-1.0-SNAPSHOT.jar /usr/local/lib/fakebank.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/fakebank.jar"]
