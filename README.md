# library-mgmt-system

here we are creating two images
1. 0.0.1-SNAPSHOT
2. latest

So in future, we will have new version with 0.0.2-snapshot and latest version will also get replaced.

docker build -f book-service/Dockerfile -t jpop/book-service:0.0.1-SNAPSHOT -t jpop/book-service:latest .