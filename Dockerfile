#
# Build stage
#
FROM maven:3.9.1-eclipse-temurin-20-alpine@sha256:b39121d48f41e71ac7c065eef669e05e79d593ac98b26bb3d73590824f4ed039 AS build
COPY . /home/app/
RUN mvn -f /home/app/pom.xml clean install -DskipTests

#
# Package stage
#
FROM eclipse-temurin:20-alpine@sha256:008fbc09ec2b578fee57dcd27938c4671ad8698710263618e404ff22b36e000d
COPY --from=build /home/app/fakebank-impl/target/fakebank-impl-1.0-SNAPSHOT.jar /usr/local/lib/fakebank.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/fakebank.jar"]
