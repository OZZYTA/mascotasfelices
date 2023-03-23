FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/OZZYTA/mascotasfelices

FROM openjdk:8-jdk-alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvn install -DskipTests

ENTRYPOINT ["java","-jar","target/mascotasfelices-0.0.1-SNAPSHOT.jar"]