CREATE TABLE IF NOT EXISTS `f1-agile`.`race` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `date` DATE NOT NULL,
    `sprint` TINYINT(1) NOT NULL,
    `id_track` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_race_track_idx` (`id_track` ASC) VISIBLE,
    CONSTRAINT `fk_race_track`
    FOREIGN KEY (`id_track`)
    REFERENCES `f1-agile`.`track` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;