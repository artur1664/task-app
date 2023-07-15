FROM maven:3.8.3-openjdk-17 AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package -DskipTests

FROM openjdk:17
COPY --from=build app/target/taskapp.jar /app-service/taskapp.jar
WORKDIR /app-service
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=test-data","taskapp.jar"]