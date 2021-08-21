FROM openjdk:8
EXPOSE 8080
WORKDIR usr/jpop
ADD ./library-service/build/libs/library-service-0.0.1-SNAPSHOT.jar libraryservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "libraryservice-0.0.1-SNAPSHOT.jar"]