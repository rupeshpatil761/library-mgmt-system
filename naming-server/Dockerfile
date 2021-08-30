FROM openjdk:8
EXPOSE 8761
WORKDIR usr/jpop
ADD ./naming-server/build/libs/naming-server-0.0.1-SNAPSHOT.jar namingserver-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "namingserver-0.0.1-SNAPSHOT.jar"]