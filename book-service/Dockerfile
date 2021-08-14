FROM openjdk:8-jdk-alpine
WORKDIR usr/jpop
COPY ./book-service/build/libs/book-service-0.0.1-SNAPSHOT.jar book-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/book-service-0.0.1-SNAPSHOT.jar"]