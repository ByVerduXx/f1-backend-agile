CREATE TABLE IF NOT EXISTS `f1-agile`.`survey` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `permalink` VARCHAR(100) NOT NULL,
    `title` VARCHAR(100) NOT NULL,
    `description` VARCHAR(500) NULL,
    `limit_date` DATETIME NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `f1-agile`.`survey_driver` (
    `id_survey` INT NOT NULL,
    `id_driver` INT NOT NULL,
    PRIMARY KEY (`id_survey`, `id_driver`),
    INDEX `fk_survey_driver_driver1_idx` (`id_driver` ASC) VISIBLE,
    INDEX `fk_survey_driver_survey1_idx` (`id_survey` ASC) VISIBLE,
    CONSTRAINT `fk_survey_driver_driver1`
    FOREIGN KEY (`id_driver`)
    REFERENCES `f1-agile`.`driver` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_survey_driver_survey1`
    FOREIGN KEY (`id_survey`)
    REFERENCES `f1-agile`.`survey` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    ) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `f1-agile`.`vote` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `id_survey` INT NOT NULL,
    `id_driver` INT NOT NULL,
    `voter_name` VARCHAR(250) NOT NULL,
    `voter_email` VARCHAR(250) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_vote_survey1_idx` (`id_survey` ASC) VISIBLE,
    INDEX `fk_vote_driver1_idx` (`id_driver` ASC) VISIBLE,
    CONSTRAINT `fk_vote_survey1`
    FOREIGN KEY (`id_survey`)
    REFERENCES `f1-agile`.`survey` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_vote_driver1`
    FOREIGN KEY (`id_driver`)
    REFERENCES `f1-agile`.`driver` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    ) ENGINE = InnoDB;
