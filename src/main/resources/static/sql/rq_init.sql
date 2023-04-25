-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema road-quest
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `road-quest` ;

-- -----------------------------------------------------
-- Schema road-quest
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `road-quest` DEFAULT CHARACTER SET utf8mb3 ;
USE `road-quest` ;

-- -----------------------------------------------------
-- Table `road-quest`.`car_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `road-quest`.`car_type` ;

CREATE TABLE IF NOT EXISTS `road-quest`.`car_type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `road-quest`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `road-quest`.`role` ;

CREATE TABLE IF NOT EXISTS `road-quest`.`role` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `road-quest`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `road-quest`.`user` ;

CREATE TABLE IF NOT EXISTS `road-quest`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(16) NOT NULL,
  `password` VARCHAR(72) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `status` TINYINT NOT NULL DEFAULT 1,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `passport` INT UNSIGNED NULL DEFAULT NULL,
  `phone_number` VARCHAR(10) NOT NULL,
  `role` INT UNSIGNED NOT NULL,
  `bill` INT UNSIGNED NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `passport_UNIQUE` (`passport` ASC) VISIBLE,
  INDEX `fk_user_role1_idx` (`role` ASC) VISIBLE,
  CONSTRAINT `fk_user_role1`
    FOREIGN KEY (`role`)
    REFERENCES `road-quest`.`role` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `road-quest`.`car`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `road-quest`.`car` ;

CREATE TABLE IF NOT EXISTS `road-quest`.`car` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `car_type` INT UNSIGNED NOT NULL,
  `vendor` INT UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_car_car_type_idx` (`car_type` ASC) VISIBLE,
  INDEX `fk_car_user1_idx` (`vendor` ASC) VISIBLE,
  CONSTRAINT `fk_car_car_type`
    FOREIGN KEY (`car_type`)
    REFERENCES `road-quest`.`car_type` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_car_user1`
    FOREIGN KEY (`vendor`)
    REFERENCES `road-quest`.`user` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `road-quest`.`rent_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `road-quest`.`rent_status` ;

CREATE TABLE IF NOT EXISTS `road-quest`.`rent_status` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `road-quest`.`application`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `road-quest`.`application` ;

CREATE TABLE IF NOT EXISTS `road-quest`.`application` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `customer` INT UNSIGNED NOT NULL,
  `car` INT NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `rent_date` DATE NULL DEFAULT NULL,
  `rent_status` INT UNSIGNED NOT NULL,
  `price` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_application_user1_idx` (`customer` ASC) VISIBLE,
  INDEX `fk_application_car1_idx` (`car` ASC) VISIBLE,
  INDEX `fk_application_rent_status1_idx` (`rent_status` ASC) VISIBLE,
  CONSTRAINT `fk_application_car1`
    FOREIGN KEY (`car`)
    REFERENCES `road-quest`.`car` (`id`),
  CONSTRAINT `fk_application_rent_status1`
    FOREIGN KEY (`rent_status`)
    REFERENCES `road-quest`.`rent_status` (`id`),
  CONSTRAINT `fk_application_user1`
    FOREIGN KEY (`customer`)
    REFERENCES `road-quest`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `road-quest`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `road-quest`;
INSERT INTO `road-quest`.`role` (`id`, `name`) VALUES (1, 'ADMIN');
INSERT INTO `road-quest`.`role` (`id`, `name`) VALUES (2, 'MANAGER');
INSERT INTO `road-quest`.`role` (`id`, `name`) VALUES (3, 'VENDOR');
INSERT INTO `road-quest`.`role` (`id`, `name`) VALUES (4, 'BASIC');

COMMIT;


-- -----------------------------------------------------
-- Data for table `road-quest`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `road-quest`;
INSERT INTO `road-quest`.`user` (`id`, `login`, `password`, `email`, `status`, `first_name`, `last_name`, `passport`, `phone_number`, `role`, `bill`) VALUES (1, 'admin', '$2a$10$Qf0cHKNVwnUJYBRuLpva1eBzifav2cZDqLp7EFxNf.yS82UsGMeum', 'admin@rqrent.com', 1, 'Danylo', 'Papizhuk', NULL, '0964234354', 1, NULL);
INSERT INTO `road-quest`.`user` (`id`, `login`, `password`, `email`, `status`, `first_name`, `last_name`, `passport`, `phone_number`, `role`, `bill`) VALUES (2, 'manager', '$2a$10$uC8kT5c68jcHMVLrQ.vKFuRaqXvYU.oRxsJxcu7TLnn7BtcpQElUW', 'manager@rqrent.com', 1, 'Yaroslav', 'Baghlaev', NULL, '0664583246', 2, NULL);
INSERT INTO `road-quest`.`user` (`id`, `login`, `password`, `email`, `status`, `first_name`, `last_name`, `passport`, `phone_number`, `role`, `bill`) VALUES (3, 'vendor', '$2a$10$1GiHKRifEF2Q2FaSgyGYi.3OkQvRqrSVndUFABHDbL5hCG2XwGmLq', 'vendor@rqrent.com', 1, 'Roman', 'Kvitka', NULL, '0951299375', 4, NULL);
INSERT INTO `road-quest`.`user` (`id`, `login`, `password`, `email`, `status`, `first_name`, `last_name`, `passport`, `phone_number`, `role`, `bill`) VALUES (4, 'basic', '$2a$10$CihwFVOJP9rfEUl.TGSRmuFxC/koTt6D9w3QUpaG9daSNg9nKPWVy', 'basic@rqrent.com', 1, 'Dmytro', 'Kalantai', NULL, '0503847621', 4, NULL);

COMMIT;

