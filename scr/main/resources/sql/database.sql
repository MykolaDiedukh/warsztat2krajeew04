-- -----------------------------------------------------
-- Schema warsztat2krajeew04
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `warsztat2krajeew04` DEFAULT CHARACTER SET utf8;
USE `warsztat2krajeew04`;

-- -----------------------------------------------------
-- Table `warsztat2krajeew04`.`users_group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `warsztat2krajeew04`.`users_group`
(
    `id`   INT(11)      NOT NULL AUTO_INCREMENT COMMENT 'The primary key of the table.',
    `name` VARCHAR(245) NULL COMMENT 'Name of group',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    COMMENT = 'Table containing user data';


-- -----------------------------------------------------
-- Table `warsztat2krajeew04`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `warsztat2krajeew04`.`users`
(
    `id`            INT(11)      NOT NULL AUTO_INCREMENT COMMENT 'The primary key of the table.',
    `username`      VARCHAR(245) NULL COMMENT 'Name and last name of user',
    `email`         VARCHAR(245) NULL UNIQUE COMMENT 'Adresa email',
    `password`      VARCHAR(60)  NULL COMMENT 'Password',
    `user_group_id` INT          NOT NULL COMMENT 'Foreign key for table users_group',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_group_id`)
        REFERENCES `warsztat2krajeew04`.`users_group` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    COMMENT = 'Table containing user data';

-- -----------------------------------------------------
-- Table `warsztat2krajeew04`.`exercises`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `warsztat2krajeew04`.`exercises`
(
    `id`          INT(11)      NOT NULL AUTO_INCREMENT COMMENT 'The primary key of the table.',
    `title`       VARCHAR(245) NULL COMMENT 'Name of exercise',
    `description` TEXT         NULL COMMENT 'exercise',
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    COMMENT = 'Table containing exercises for users';

-- -----------------------------------------------------
-- Table `warsztat2krajeew04`.`solutions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `warsztat2krajeew04`.`solutions`
(
    `id`          INT(11)  NOT NULL AUTO_INCREMENT COMMENT 'The primary key of the table.',
    `created`     DATETIME NULL COMMENT 'Time of created solution',
    `updated`     DATETIME NULL COMMENT 'Time of updated solution',
    `description` TEXT     NULL COMMENT 'solution',
    `exercise_id` INT      NOT NULL COMMENT 'Foreign key for table exercises',
    `user_id`     INT      NOT NULL COMMENT 'Foreign key for table user',
    PRIMARY KEY (id),
    FOREIGN KEY (`exercise_id`) REFERENCES `warsztat2krajeew04`.`exercises` (id),
    FOREIGN KEY (`user_id`) REFERENCES `warsztat2krajeew04`.`users` (id)
)
    ENGINE = InnoDB
    COMMENT = 'Table containing solutions for exercises';