-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema gefi
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gefi
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gefi` DEFAULT CHARACTER SET utf8 ;
USE `gefi` ;

-- -----------------------------------------------------
-- Table `gefi`.`evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gefi`.`evento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(50) NOT NULL,
  `dataI` DATE NOT NULL,
  `dataF` DATE NOT NULL,
  `local` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gefi`.`aval_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gefi`.`aval_evento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `satisfacao` VARCHAR(5) NOT NULL,
  `adequacao` VARCHAR(5) NOT NULL,
  `abrangencia` VARCHAR(5) NOT NULL,
  `relevancia` VARCHAR(5) NOT NULL,
  `qualidade` VARCHAR(5) NOT NULL,
  `atuac_coord` VARCHAR(5) NOT NULL,
  `evento_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_aval_evento_evento1_idx` (`evento_id` ASC),
  CONSTRAINT `fk_aval_evento_evento1`
    FOREIGN KEY (`evento_id`)
    REFERENCES `gefi`.`evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gefi`.`apoio_logistico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gefi`.`apoio_logistico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `qualidade` VARCHAR(5) NOT NULL,
  `adequacao` VARCHAR(5) NOT NULL,
  `percentual_obj` VARCHAR(5) NOT NULL,
  `criticas_sug` VARCHAR(100) NOT NULL,
  `evento_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_apoio_logistico_evento1_idx` (`evento_id` ASC),
  CONSTRAINT `fk_apoio_logistico_evento1`
    FOREIGN KEY (`evento_id`)
    REFERENCES `gefi`.`evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gefi`.`tema_curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gefi`.`tema_curso` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(50) NOT NULL,
  `evento_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tema_curso_evento1_idx` (`evento_id` ASC),
  CONSTRAINT `fk_tema_curso_evento1`
    FOREIGN KEY (`evento_id`)
    REFERENCES `gefi`.`evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gefi`.`aval_curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gefi`.`aval_curso` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pontualidade` VARCHAR(5) NOT NULL,
  `dominio_assunto` VARCHAR(5) NOT NULL,
  `clareza` VARCHAR(5) NOT NULL,
  `distr_ch` VARCHAR(5) NOT NULL,
  `motiv_grupo` VARCHAR(5) NOT NULL,
  `escl_duvidas` VARCHAR(5) NOT NULL,
  `tema_curso_id` INT NOT NULL,
  `evento_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_aval_curso_temas_curso_idx` (`tema_curso_id` ASC),
  INDEX `fk_aval_curso_evento1_idx` (`evento_id` ASC),
  CONSTRAINT `fk_aval_curso_temas_curso`
    FOREIGN KEY (`tema_curso_id`)
    REFERENCES `gefi`.`tema_curso` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aval_curso_evento1`
    FOREIGN KEY (`evento_id`)
    REFERENCES `gefi`.`evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
