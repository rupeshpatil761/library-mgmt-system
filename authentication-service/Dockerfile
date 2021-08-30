FROM openjdk:8
EXPOSE 9094
WORKDIR usr/jpop
ADD ./authentication-service/build/libs/authentication-service-0.0.1-SNAPSHOT.jar authenticationservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "authenticationservice-0.0.1-SNAPSHOT.jar"]