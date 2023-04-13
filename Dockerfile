#
# Build stage
#
FROM maven:3.9.1-eclipse-temurin-20-alpine@sha256:ecf432ccf294d35e91256ba59463dee3c3f7e7f45704799424f29aefe19398d6 AS build
COPY . /home/app/
RUN mvn -f /home/app/pom.xml clean install -DskipTests

#
# Package stage
#
FROM eclipse-temurin:20-alpine@sha256:008fbc09ec2b578fee57dcd27938c4671ad8698710263618e404ff22b36e000d
COPY --from=build /home/app/fakebank-impl/target/fakebank-impl-1.0-SNAPSHOT.jar /usr/local/lib/fakebank.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/fakebank.jar"]
