DROP DATABASE IF EXISTS dvd_library_test;
CREATE DATABASE dvd_library_test;

USE dvd_library_test;

CREATE TABLE IF NOT EXISTS dvd (
	id INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    release_date DATE NOT NULL,
    director_id INT(10) NOT NULL,
    studio VARCHAR(50) NOT NULL,
    mpaa_rating VARCHAR(5) NOT NULL,
    notes VARCHAR(50) NULL
);

CREATE TABLE IF NOT EXISTS director (
	id INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

ALTER TABLE `dvd_library_test`.`dvd` 
ADD INDEX `fk_dvd_director_idx` (`director_id` ASC);
ALTER TABLE `dvd_library_test`.`dvd` 
ADD CONSTRAINT `fk_dvd_director`
  FOREIGN KEY (`director_id`)
  REFERENCES `dvd_library_test`.`director` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
CREATE TABLE IF NOT EXISTS dvd_director (
	id INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    director_id INT(10) NOT NULL,
    dvd_id INT(10) NOT NULL
);  

ALTER TABLE `dvd_library_test`.`dvd_director` 
ADD INDEX `fk_dvd_director_dvd_idx` (`dvd_id` ASC),
ADD INDEX `fk_dvd_director_director_idx` (`director_id` ASC);
ALTER TABLE `dvd_library_test`.`dvd_director` 
ADD CONSTRAINT `fk_dvd_director_dvd`
  FOREIGN KEY (`dvd_id`)
  REFERENCES `dvd_library_test`.`dvd` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_dvd_director_director`
  FOREIGN KEY (`director_id`)
  REFERENCES `dvd_library_test`.`director` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
