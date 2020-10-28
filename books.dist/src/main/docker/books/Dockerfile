FROM openjdk:8-jdk-alpine

RUN apk --no-cache add curl

RUN addgroup -S books && adduser -S books -G books
USER books:books
COPY books.app.jar books.app.jar
ENTRYPOINT ["java","-jar","/books.app.jar"]

# Expose port 8080
EXPOSE 8080
