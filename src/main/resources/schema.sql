-- -----------------------------------------------------
-- Table ACCOUNT
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `account` (
  `id` VARCHAR(36) NOT NULL,
  `first_name` VARCHAR(80) NULL,
  `last_name` VARCHAR(80) NULL,
  `email` VARCHAR(45) NULL,
  `secret` BLOB NULL,
  `create_date` DATE NULL,
  `change_date` DATE NULL,
  `deleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table CHEF
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chef` (
  `id` VARCHAR(36) NOT NULL,
  `status` VARCHAR(20) NULL,
  `rfc` VARCHAR(13) NULL,
  `curp` VARCHAR(18) NULL,
  `rating` FLOAT NULL,
  `create_date` DATE NULL,
  `change_date` DATE NULL,
  `deleted` bit(1) DEFAULT NULL,
  `fk_id_account` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_chef_account`
    FOREIGN KEY (`fk_id_account`)
    REFERENCES `account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table TELEPHONE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `telephone` (
  `id` VARCHAR(36) NOT NULL,
  `name` VARCHAR(15) NULL,
  `number` VARCHAR(15) NULL,
  `lada` VARCHAR(5) NULL,
  `type` VARCHAR(10) NULL,
  `fk_id_chef` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_telephones_chef`
    FOREIGN KEY (`fk_id_chef`)
    REFERENCES `chef` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table CLIENT
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `client` (
  `id` VARCHAR(36) NOT NULL,
  `status` VARCHAR(20) NULL,
  `first_name` VARCHAR(80) NULL,
  `last_name` VARCHAR(80) NULL,
  `email` VARCHAR(45) NULL,
  `rating` FLOAT NULL,
  `create_date` DATE NULL,
  `change_date` DATE NULL,
  `deleted` bit(1) DEFAULT NULL,
  `fk_id_telephone` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_client_telephone`
    FOREIGN KEY (`fk_id_telephone`)
    REFERENCES `telephone` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;