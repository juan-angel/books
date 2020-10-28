
USE books;

CREATE USER 'books'@'%' IDENTIFIED BY 'books';
GRANT SELECT, INSERT, UPDATE, DELETE ON books.* TO 'books'@'%';
