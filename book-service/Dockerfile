FROM openjdk:8
EXPOSE 9092
WORKDIR usr/jpop
ADD ./book-service/build/libs/book-service-0.0.1-SNAPSHOT.jar bookservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "bookservice-0.0.1-SNAPSHOT.jar"]