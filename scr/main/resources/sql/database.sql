-- -----------------------------------------------------
-- Schema warsztat2krajeew04
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `warsztat2krajeew04` DEFAULT CHARACTER SET utf8 ;
USE `warsztat2krajeew04` ;

-- -----------------------------------------------------
-- Table `warsztat2krajeew04`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `warsztat2krajeew04`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'The primary key of the table.',
  `username` VARCHAR(245) NULL COMMENT 'Name and last name of user',
  `email` VARCHAR(245) NULL COMMENT 'Adresa email',
  `password` VARCHAR(60) NULL COMMENT 'Password',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = 'Table containing user data';

-- -----------------------------------------------------
-- Table `warsztat2krajeew04`.`users_group`
-- -----------------------------------------------------
