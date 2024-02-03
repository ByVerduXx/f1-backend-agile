CREATE TABLE IF NOT EXISTS  `f1-agile`.`news` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    permalink VARCHAR(100) NOT NULL,
    title VARCHAR(100) NOT NULL,
    image VARCHAR(255) NOT NULL,
    text VARCHAR(2000) NOT NULL,
    publication_date DATE)
    ENGINE = InnoDB;