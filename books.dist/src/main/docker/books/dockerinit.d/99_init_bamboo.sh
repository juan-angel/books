#!/bin/sh

# Run books
echo "Launching books"
java -Xmx1g -cp /etc/example/books:/etc/example/books/books.rest.jar -Dloader.main=com.example.books.BooksApplication org.springframework.boot.loader.JarLauncher
