--
-- Use books schema
--
USE books;

-- Clean schema if it exists by dropping their tables
DROP TABLE IF EXISTS `book`;

CREATE TABLE book (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	author VARCHAR(100)
) ENGINE=INNODB  CHARSET=utf8;
