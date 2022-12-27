Drop Table if exists BOOK;

CREATE TABLE BOOK
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    book_name   VARCHAR(100) NOT NULL,
    pages       INT NOT NULL,
    price       DECIMAL      NOT NULL,
    author      VARCHAR(100) NOT NULL,
    iban_number VARCHAR(100) NOT NULL
);

