# books
Sample Spring Boot project to demonstrate docker and docker-compose capabilities. It has the modules:
- books.app: It contains the app
- books.dist: It contains the info to package the app

## How to compile:
mvn clean install -DskipTests -Pbooks\[.dev|.demo\]

## How to build and run everything without docker-compose:
```bash
# Building the docker image for BD
cd books.dist/src/main/docker/mariadb
docker build --tag books-mariadb:0.0.1-SNAPSHOT .

# Create volume for DB
docker volume create booksdev_dev.mariadb.books.database

# Create a network
docker network create --driver=bridge --subnet=172.18.0.0/16 books-network

# How to run a docker container of the docker DB image:
docker run -itd -e MYSQL_ROOT_PASSWORD=mypassword -v booksdev_dev.mariadb.books.database:/var/lib/mysql --name dev.mariadb.books --network=books-network books-mariadb:0.0.1-SNAPSHOT

# Building the docker image for books.app
cp books.dist/src/main/docker/books/Dockerfile books.app/target/
cd books.app/target/
docker build --tag books-dev:0.0.1-SNAPSHOT .

# How to run a docker container of the docker DB image:
docker run -itd --name dev.books --network=books-network books-dev:0.0.1-SNAPSHOT

# How to stop the docker instance:
docker stop dev.mariadb.books && docker rm dev.mariadb.books && docker stop dev.books && docker rm dev.books

# How to remove all images:
docker rmi $(docker images -q)

# How to remove all volumes:
docker volume prune

#How to remove all networks:
docker network prune
```

## How to compile building docker images:

### dev
Build the image:
```bash
mvn clean install -DskipTests -Pbooks.dev -Dbuild.docker.images
```
Create a container and run it:
```bash
docker-compose -p books.dev -f books.dist/target/compose-filtered/docker-compose-dev.yml up -d
```
Note: -Ddocker.X creates the compose files with the required compose info. Also, you can build "only" the compose files like this:
```bash
mvn clean compile -f books.dist/pom.xml -Dbuild.compose.file -Pbooks.dev
```

Access the container (via loca IP of the container, like 172.18.0.3):
```bash
docker inspect <container_id>
# Look for the internal IP, like 172.17.0.3
http://172.18.0.3:8080/swagger-ui.html
```

Stop the container:
```bash
docker-compose -p books.dev -f books.dist/target/compose-filtered/docker-compose-dev.yml down
```

### demo
Build the image:
```bash
mvn clean install -DskipTests -Pbooks.demo -Dbuild.docker.images
```
Create a container and run it:
```bash
docker-compose -p books.demo -f books.dist/target/compose-filtered/docker-compose-demo.yml up -d
```
Note: -Ddocker.X creates the compose files with the required compose info. Also, you can build "only" the compose files like this:
```bash
mvn clean compile -f books.dist/pom.xml -Dbuild.compose.file -Pbooks.demo
```

Access the container (via loca IP of the container, like 172.18.0.3):
```bash
docker inspect <container_id>
# Look for the internal IP, like 172.17.0.3
http://172.18.0.3:8080/swagger-ui.html
```

Stop the container:
```bash
docker-compose -p books.demo -f books.dist/target/compose-filtered/docker-compose-demo.yml down
```
