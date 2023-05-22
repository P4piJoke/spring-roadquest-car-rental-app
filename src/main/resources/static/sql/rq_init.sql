-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

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
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE UNIQUE INDEX `name_UNIQUE` ON `road-quest`.`car_type` (`name` ASC) VISIBLE;

CREATE UNIQUE INDEX `id_UNIQUE` ON `road-quest`.`car_type` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `road-quest`.`car`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `road-quest`.`car` ;

CREATE TABLE IF NOT EXISTS `road-quest`.`car` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `car_type` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_car_car_type`
    FOREIGN KEY (`car_type`)
    REFERENCES `road-quest`.`car_type` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

CREATE UNIQUE INDEX `id_UNIQUE` ON `road-quest`.`car` (`id` ASC) VISIBLE;

CREATE INDEX `fk_car_car_type_idx` ON `road-quest`.`car` (`car_type` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `road-quest`.`rent_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `road-quest`.`rent_status` ;

CREATE TABLE IF NOT EXISTS `road-quest`.`rent_status` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb3;

CREATE UNIQUE INDEX `id_UNIQUE` ON `road-quest`.`rent_status` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `road-quest`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `road-quest`.`role` ;

CREATE TABLE IF NOT EXISTS `road-quest`.`role` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;

CREATE UNIQUE INDEX `id_UNIQUE` ON `road-quest`.`role` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `road-quest`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `road-quest`.`user` ;

CREATE TABLE IF NOT EXISTS `road-quest`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(16) NOT NULL,
  `password` VARCHAR(72) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `status` TINYINT NOT NULL DEFAULT '1',
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `passport` INT UNSIGNED NULL DEFAULT NULL,
  `phone_number` VARCHAR(10) NOT NULL,
  `role` INT UNSIGNED NOT NULL,
  `bill` INT UNSIGNED NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_role1`
    FOREIGN KEY (`role`)
    REFERENCES `road-quest`.`role` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;

CREATE UNIQUE INDEX `id_UNIQUE` ON `road-quest`.`user` (`id` ASC) VISIBLE;

CREATE UNIQUE INDEX `username_UNIQUE` ON `road-quest`.`user` (`login` ASC) VISIBLE;

CREATE UNIQUE INDEX `passport_UNIQUE` ON `road-quest`.`user` (`passport` ASC) VISIBLE;

CREATE INDEX `fk_user_role1_idx` ON `road-quest`.`user` (`role` ASC) VISIBLE;


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

CREATE UNIQUE INDEX `id_UNIQUE` ON `road-quest`.`application` (`id` ASC) VISIBLE;

CREATE INDEX `fk_application_user1_idx` ON `road-quest`.`application` (`customer` ASC) VISIBLE;

CREATE INDEX `fk_application_car1_idx` ON `road-quest`.`application` (`car` ASC) VISIBLE;

CREATE INDEX `fk_application_rent_status1_idx` ON `road-quest`.`application` (`rent_status` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `road-quest`.`rent_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `road-quest`;
INSERT INTO `road-quest`.`rent_status` (`id`, `name`) VALUES (1, 'New');
INSERT INTO `road-quest`.`rent_status` (`id`, `name`) VALUES (2, 'On hold');
INSERT INTO `road-quest`.`rent_status` (`id`, `name`) VALUES (3, 'Paid');
INSERT INTO `road-quest`.`rent_status` (`id`, `name`) VALUES (4, 'Completed');
INSERT INTO `road-quest`.`rent_status` (`id`, `name`) VALUES (5, 'Refunded');

COMMIT;

-- -----------------------------------------------------
-- Data for table `road-quest`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `road-quest`;
INSERT INTO `road-quest`.`role` (`id`, `name`) VALUES (1, 'ADMIN');
INSERT INTO `road-quest`.`role` (`id`, `name`) VALUES (2, 'MANAGER');
INSERT INTO `road-quest`.`role` (`id`, `name`) VALUES (3, 'BASIC');

COMMIT;


-- -----------------------------------------------------
-- Data for table `road-quest`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `road-quest`;
INSERT INTO `road-quest`.`user` (`id`, `login`, `password`, `email`, `status`, `first_name`, `last_name`, `passport`, `phone_number`, `role`, `bill`) VALUES (1, 'admin', '$2a$10$Qf0cHKNVwnUJYBRuLpva1eBzifav2cZDqLp7EFxNf.yS82UsGMeum', 'admin@rqrent.com', 1, 'Danylo', 'Papizhuk', NULL, '0964234354', 1, NULL);
INSERT INTO `road-quest`.`user` (`id`, `login`, `password`, `email`, `status`, `first_name`, `last_name`, `passport`, `phone_number`, `role`, `bill`) VALUES (2, 'manager', '$2a$10$uC8kT5c68jcHMVLrQ.vKFuRaqXvYU.oRxsJxcu7TLnn7BtcpQElUW', 'manager@rqrent.com', 1, 'Yaroslav', 'Baghlaev', NULL, '0664583246', 2, NULL);
INSERT INTO `road-quest`.`user` (`id`, `login`, `password`, `email`, `status`, `first_name`, `last_name`, `passport`, `phone_number`, `role`, `bill`) VALUES (3, 'basic', '$2a$10$JdWhzqHGYE94hEzN.cnNHOGN7yBKoTLMDdshGX/fenTnXuGiTOjaa', 'basic@rqrent.com', 1, 'Roman', 'Kvitka', NULL, '0951299375', 3, NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `road-quest`.`car_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `road-quest`;
INSERT INTO `road-quest`.`car_type` (`id`, `name`) VALUES (1, 'Soft');
INSERT INTO `road-quest`.`car_type` (`id`, `name`) VALUES (2, 'Family');
INSERT INTO `road-quest`.`car_type` (`id`, `name`) VALUES (3, 'Off-road');
INSERT INTO `road-quest`.`car_type` (`id`, `name`) VALUES (4, 'Super');

COMMIT;