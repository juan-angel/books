# books
Sample Spring Boot project to demonstrate docker and docker-compose capabilities. It has the modules:
- books.app: It contains the app
- books.dist: It contains the info to package the app

## How to compile:
mvn clean install -DskipTests -Pbooks\[.dev|.demo\]

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

## Alternative commands:

### How to run a docker container of the docker DB image:
docker run -it -e MYSQL_ROOT_PASSWORD=mypasswd -v booksdev_dev.mariadb.books.database books-mariadb:0.0.1-SNAPSHOT