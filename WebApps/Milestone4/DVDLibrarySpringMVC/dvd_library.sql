#DROP DATABASE IF EXISTS dvd_library;

CREATE DATABASE IF NOT EXISTS dvd_library;

USE dvd_library;

CREATE TABLE IF NOT EXISTS `dvd_library`.`dvd` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `releaseDate` DATE NOT NULL,
  `mpaaRating` VARCHAR(45) NOT NULL,
  `director` VARCHAR(45) NOT NULL,
  `studio` VARCHAR(45) NOT NULL,
  `notes` VARCHAR(250) NULL,
  PRIMARY KEY (`id`));

