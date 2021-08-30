FROM openjdk:8
EXPOSE 8888
WORKDIR usr/jpop
ADD ./config-server/build/libs/config-server-0.0.1-SNAPSHOT.jar configserver-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "configserver-0.0.1-SNAPSHOT.jar"]