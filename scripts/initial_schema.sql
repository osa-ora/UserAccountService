CREATE SCHEMA `Accounts` ;
CREATE TABLE `Accounts`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

INSERT INTO `accounts`.`account` (`id`, `login`, `password`, `email`) VALUES ('1', 'Osama', '123', 'osa.ora@acme.com');
INSERT INTO `accounts`.`account` (`id`, `login`, `password`, `email`) VALUES ('2', 'Sameh', '123', 'sa.or@acme.com');
commit;

GRANT ALL PRIVILEGES ON *.* TO 'accounts'@'localhost' IDENTIFIED BY 'accounts';

