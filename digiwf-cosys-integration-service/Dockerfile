FROM registry.access.redhat.com/ubi8/openjdk-11:latest

EXPOSE 8080

RUN mkdir /app

COPY target/*.jar /app/spring-boot-application.jar

CMD ["java","-jar","/app/spring-boot-application.jar"]
