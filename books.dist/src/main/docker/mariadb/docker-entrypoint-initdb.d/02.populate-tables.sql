--
-- Use books
--
USE books;

--
-- Inserting data for table `book`
--
LOCK TABLES `book` WRITE;
INSERT INTO `book` (id, title, author) VALUES (1, 'title 1', 'author 1');
INSERT INTO `book` (id, title, author) VALUES (2, 'title 2', 'author 2');
INSERT INTO `book` (id, title, author) VALUES (3, 'title 3', 'author 3');
UNLOCK TABLES;

