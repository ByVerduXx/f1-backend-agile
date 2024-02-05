CREATE TABLE IF NOT EXISTS  `f1-agile`.`news` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    permalink VARCHAR(100) UNIQUE NOT NULL,
    title VARCHAR(100) NOT NULL,
    image VARCHAR(255),
    text TEXT CHECK(LENGTH(text) >= 500 AND LENGTH(text) <= 2000),
    publication_date DATE)
    ENGINE = InnoDB;