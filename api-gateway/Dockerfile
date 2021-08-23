FROM openjdk:8
EXPOSE 8765
WORKDIR usr/jpop
ADD ./api-gateway/build/libs/api-gateway-0.0.1-SNAPSHOT.jar apigateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "apigateway-0.0.1-SNAPSHOT.jar"]