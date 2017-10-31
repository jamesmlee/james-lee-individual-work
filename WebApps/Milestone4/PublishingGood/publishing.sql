CREATE DATABASE IF NOT EXISTS publishing;

USE publishing;

/*
CREATE TABLE IF NOT EXISTS `publishing`.`book_publisher` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `publisher_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `index3` (`publisher_id` ASC, `book_id` ASC),
  INDEX `fk_publisher_book_2_idx` (`book_id` ASC),
  CONSTRAINT `fk_publisher_book_1`
    FOREIGN KEY (`publisher_id`)
    REFERENCES `publishing`.`publisher` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_publisher_book_2`
    FOREIGN KEY (`book_id`)
    REFERENCES `publishing`.`book` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
*/
    
# INSERT INTO publishing.publisher values(1, "Random House");

/*
SELECT b.* FROM book b
INNER JOIN author_book ab ON ab.book_id = b.id
WHERE ab.author_id = 1;
# start at record 0 and return 10 records LIMIT 
LIMIT 0, 10;
*/