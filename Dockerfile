#
# Build stage
#
FROM maven:3.9.1-eclipse-temurin-20-alpine@sha256:b39121d48f41e71ac7c065eef669e05e79d593ac98b26bb3d73590824f4ed039 AS build
COPY . /home/app/
RUN mvn -f /home/app/pom.xml clean install -DskipTests

#
# Package stage
#
FROM eclipse-temurin:20-alpine@sha256:8f2fcf1bbafd37d4d2631c8baeaff06048308486d534ba7d09148d7a4d351956
COPY --from=build /home/app/fakebank-impl/target/fakebank-impl-1.0-SNAPSHOT.jar /usr/local/lib/fakebank.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/fakebank.jar"]
