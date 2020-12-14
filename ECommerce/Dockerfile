FROM openjdk:8-jdk-alpine

ENV ACTIVE_PROFILE=local

RUN addgroup -S spring && adduser -S spring -G spring

USER spring:spring

ARG JAR_FILE=target/*.jar

COPY target/*.jar app.jar

EXPOSE 8080 8080

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${ACTIVE_PROFILE}","/app.jar"]