FROM openjdk:8
EXPOSE 9091
WORKDIR usr/jpop
ADD ./user-service/build/libs/user-service-0.0.1-SNAPSHOT.jar userservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "userservice-0.0.1-SNAPSHOT.jar"]