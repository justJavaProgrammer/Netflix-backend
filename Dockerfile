FROM maven:3.8.5-jdk-11-slim

WORKDIR auth-microservice
COPY . .

ENTRYPOINT ["mvn", "spring-boot:run"]
