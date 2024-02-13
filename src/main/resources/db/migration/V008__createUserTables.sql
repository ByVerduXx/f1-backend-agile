CREATE TABLE IF NOT EXISTS `f1-agile`.`role` (
                                                 `id` INT NOT NULL AUTO_INCREMENT,
                                                 `role_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC) VISIBLE)

ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `f1-agile`.`user` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(100) NOT NULL,
    `password` VARCHAR(256) NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `last_name` VARCHAR(45) NOT NULL,
    `username` VARCHAR(45) NOT NULL,
    `role_id` INT NOT NULL,
    `team_id` INT NULL,
    `validated` TINYINT NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
    INDEX `fk_user_role1_idx` (`role_id` ASC) VISIBLE,
    INDEX `fk_user_team1_idx` (`team_id` ASC) VISIBLE,
    CONSTRAINT `fk_user_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `f1-agile`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_user_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `f1-agile`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


INSERT INTO `f1-agile`.`role` (`id`, `role_name`) VALUES (1, 'ROLE_ADMIN');
INSERT INTO `f1-agile`.`role` (`id`, `role_name`) VALUES (2, 'ROLE_MANAGER');
INSERT INTO `f1-agile`.`role` (`id`, `role_name`) VALUES (3, 'ROLE_USER');