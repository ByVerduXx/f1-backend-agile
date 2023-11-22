CREATE TABLE IF NOT EXISTS `mydb`.`user` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(100) NOT NULL,
    `password` VARCHAR(256) NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `last_name` VARCHAR(45) NOT NULL,
    `username` VARCHAR(45) NOT NULL,
    `role_id` INT NOT NULL,
    `validated` TINYINT NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
    INDEX `fk_user_role1_idx` (`role_id` ASC) VISIBLE,
    CONSTRAINT `fk_user_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `mydb`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`role` (
   `id` INT NOT NULL AUTO_INCREMENT,
   `role_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC) VISIBLE);

ENGINE = InnoDB;

INSERT INTO `mydb`.`role` (`id`, `role_name`) VALUES (1, 'ROLE_ADMIN');
INSERT INTO `mydb`.`role` (`id`, `role_name`) VALUES (2, 'ROLE_MANAGER');
INSERT INTO `mydb`.`role` (`id`, `role_name`) VALUES (3, 'ROLE_USER');