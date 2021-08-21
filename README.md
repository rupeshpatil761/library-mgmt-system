# library-mgmt-system

book-service:9092
user-service:9091
library-service:8080
api-gateway:8765
eureka-server:8761
config-server:8888

here we are creating two images
1. 0.0.1-SNAPSHOT
2. latest

So in future, we will have new version with 0.0.2-snapshot and latest version will also get replaced.

docker build -f book-service/Dockerfile -t jpop/book-service:0.0.1-SNAPSHOT -t jpop/book-service:latest .

Commands to build docker images. 

Before executing below commands make sure you have jars available under /build/libs folder

docker build -f book-service/Dockerfile -t jpop/book-service:latest .
docker build -f user-service/Dockerfile -t jpop/user-service:latest .
docker build -f library-service/Dockerfile -t jpop/library-service:latest .
docker build -f api-gateway/Dockerfile -t jpop/api-gateway:latest .
docker build -f naming-server/Dockerfile -t jpop/eureka-server:latest .
docker build -f config-server/Dockerfile -t jpop/config-server:latest .

docker-compose up