## Prerequisite:
1. java 17 installed
2. maven installed

## Start application:
1. clone project from: https://github.com/artur1664/task-app.git
2. go to directory with pom file
3. execute: mvn clean install OR mvn clean install -DskipTests
4. start jar from target folder with command: java -jar app-1.0.jar
if you want to start application with random test data add profile test-data to java command:
java -jar -Dspring.profiles.active=test-data app-1.0.jar 

## Start application with docker compose
1. clone project from: https://github.com/artur1664/task-app.git
2. go to project directory with docker-compose.yml file
3. execute: docker-compose up -d new container with name task-app should be running

## Rest api overview:
Once application is started on local computer go to: http://localhost:8080/swagger-ui/index.html

## H2 database
1. go to http://localhost:8080/h2-console/
2. use Setting and Name Generic H2 Embedded
3. Driver class: org.h2.Driver
4. JDBC url: jdbc:h2:mem:app_db
5. user: user
6. password: password