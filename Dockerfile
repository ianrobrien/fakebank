#
# Build stage
#
FROM maven:3.9.2-eclipse-temurin-20-alpine@sha256:7fe67b554e7a7961498005a2eace9e49f4950ef657c701babe9bcd07f789e093 AS build
COPY . /home/app/
RUN mvn -f /home/app/pom.xml clean install -DskipTests

#
# Package stage
#
FROM eclipse-temurin:20-alpine@sha256:83f609aa050cce0490f10c07d36548e12973a9100d7144b87fb16f2897ff323a
COPY --from=build /home/app/fakebank-impl/target/fakebank-impl-1.0-SNAPSHOT.jar /usr/local/lib/fakebank.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/fakebank.jar"]
