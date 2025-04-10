FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-11-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install -DskipTests

FROM openjdk:11-jdk-slim

EXPOSE 8080

COPY --from=build /target/CaioHenrique-0.0.1-SNAPSHOT.jar api.jar

ENTRYPOINT [ "java", "-jar", "api.jar" ]