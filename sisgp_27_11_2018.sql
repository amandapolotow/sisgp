-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sisgp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sisgp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sisgp` DEFAULT CHARACTER SET utf8 ;
USE `sisgp` ;

-- -----------------------------------------------------
-- Table `sisgp`.`nivel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`nivel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nivel` VARCHAR(255) NULL,
  `st_ativo` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisgp`.`grupos_acesso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`grupos_acesso` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `grupo_acesso` VARCHAR(255) NULL COMMENT 'Funcionario, Gestor, Gestor Geral',
  `st_ativo` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisgp`.`recursos_humanos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`recursos_humanos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NULL,
  `sobrenome` VARCHAR(255) NULL,
  `email` VARCHAR(255) NULL,
  `senha` VARCHAR(255) NULL,
  `telefone` VARCHAR(255) NULL COMMENT 'Campo temporário!! Até eu descobrir como salva multiplos registros via Java',
  `cargo` VARCHAR(255) NULL,
  `id_nivel` INT NOT NULL,
  `id_grupo_acesso` INT NOT NULL,
  `vl_hora` DECIMAL(8,2) NULL,
  `vl_hora_extra` DECIMAL(8,2) NULL,
  `observacao` VARCHAR(255) NULL,
  `st_ativo` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_recursos_humanos_nivel1_idx` (`id_nivel` ASC),
  INDEX `fk_recursos_humanos_grupo_acesso1_idx` (`id_grupo_acesso` ASC),
  CONSTRAINT `fk_recursos_humanos_nivel1`
    FOREIGN KEY (`id_nivel`)
    REFERENCES `sisgp`.`nivel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recursos_humanos_grupo_acesso1`
    FOREIGN KEY (`id_grupo_acesso`)
    REFERENCES `sisgp`.`grupos_acesso` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisgp`.`recursos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`recursos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(255) NULL,
  `qtd_estoque` VARCHAR(45) NULL,
  `observacao` VARCHAR(255) NULL,
  `st_ativo` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisgp`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`clientes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `razao_social` VARCHAR(255) NULL,
  `nome_fantasia` VARCHAR(255) NULL,
  `cnpj` VARCHAR(255) NULL,
  `email` VARCHAR(255) NULL,
  `telefone` VARCHAR(255) NULL,
  `website` VARCHAR(255) NULL,
  `responsavel` VARCHAR(255) NULL,
  `observacao` VARCHAR(255) NULL,
  `st_ativo` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisgp`.`status_projeto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`status_projeto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(255) NULL,
  `st_ativo` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisgp`.`projetos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`projetos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_rh_gestor` INT NOT NULL,
  `id_cliente` INT NOT NULL,
  `codigo` VARCHAR(255) NULL,
  `nome` VARCHAR(45) NULL,
  `descricao` VARCHAR(255) NULL,
  `id_status_projeto` INT NOT NULL,
  `st_ativo` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_projetos_clientes1_idx` (`id_cliente` ASC),
  INDEX `fk_projetos_status_projeto1_idx` (`id_status_projeto` ASC),
  INDEX `fk_projetos_recursos_humanos1_idx` (`id_rh_gestor` ASC),
  CONSTRAINT `fk_projetos_clientes1`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `sisgp`.`clientes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_projetos_status_projeto1`
    FOREIGN KEY (`id_status_projeto`)
    REFERENCES `sisgp`.`status_projeto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_projetos_recursos_humanos1`
    FOREIGN KEY (`id_rh_gestor`)
    REFERENCES `sisgp`.`recursos_humanos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisgp`.`status_atividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`status_atividade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(255) NULL,
  `st_ativo` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisgp`.`status_sistema_atividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`status_sistema_atividade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(255) NULL,
  `st_ativo` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisgp`.`atividades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`atividades` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_projeto` INT NOT NULL,
  `codigo` VARCHAR(255) NULL,
  `nome` VARCHAR(255) NULL,
  `descricao` VARCHAR(255) NULL,
  `id_status_atividade` INT NOT NULL,
  `id_st_sist_atividade` INT NOT NULL,
  `st_ativo` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_atividades_status_atividade1_idx` (`id_status_atividade` ASC),
  INDEX `fk_atividades_projetos1_idx` (`id_projeto` ASC),
  INDEX `fk_atividades_st_sist_atividade1_idx` (`id_st_sist_atividade` ASC),
  CONSTRAINT `fk_atividades_status_atividade1`
    FOREIGN KEY (`id_status_atividade`)
    REFERENCES `sisgp`.`status_atividade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_atividades_projetos1`
    FOREIGN KEY (`id_projeto`)
    REFERENCES `sisgp`.`projetos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_atividades_st_sist_atividade1`
    FOREIGN KEY (`id_st_sist_atividade`)
    REFERENCES `sisgp`.`status_sistema_atividade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisgp`.`sub_atividades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`sub_atividades` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_atividade` INT NOT NULL,
  `id_st_sist_atividade` INT NOT NULL,
  `id_status_atividade` INT NOT NULL,
  `id_recurso_humano` INT NOT NULL,
  `codigo` VARCHAR(255) NULL,
  `nome` VARCHAR(255) NULL,
  `descricao` VARCHAR(255) NULL,
  `nr_horas_previsto` INT NULL,
  `dt_inicio` DATE NULL,
  `dt_fim` DATE NULL,
  `st_ativo` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sub_atividade_atividades1_idx` (`id_atividade` ASC),
  INDEX `fk_sub_atividades_status_sistema_atividade1_idx` (`id_st_sist_atividade` ASC),
  INDEX `fk_sub_atividades_status_atividade1_idx` (`id_status_atividade` ASC),
  INDEX `fk_sub_atividades_recursos_humanos1_idx` (`id_recurso_humano` ASC),
  CONSTRAINT `fk_sub_atividade_atividades1`
    FOREIGN KEY (`id_atividade`)
    REFERENCES `sisgp`.`atividades` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_atividades_status_sistema_atividade1`
    FOREIGN KEY (`id_st_sist_atividade`)
    REFERENCES `sisgp`.`status_sistema_atividade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_atividades_status_atividade1`
    FOREIGN KEY (`id_status_atividade`)
    REFERENCES `sisgp`.`status_atividade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sub_atividades_recursos_humanos1`
    FOREIGN KEY (`id_recurso_humano`)
    REFERENCES `sisgp`.`recursos_humanos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisgp`.`categoria_contas_pagar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`categoria_contas_pagar` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `categoria` VARCHAR(255) NULL,
  `st_ativo` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisgp`.`categoria_contas_receber`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`categoria_contas_receber` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `categoria` VARCHAR(255) NULL,
  `st_ativo` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisgp`.`recursos_projetos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`recursos_projetos` (
  `id_recurso` INT NOT NULL,
  `id_projeto` INT NOT NULL,
  `quantidade` INT NULL,
  `st_ativo` TINYINT NULL,
  PRIMARY KEY (`id_recurso`, `id_projeto`),
  INDEX `fk_recursos_has_projetos_projetos1_idx` (`id_projeto` ASC),
  INDEX `fk_recursos_has_projetos_recursos1_idx` (`id_recurso` ASC),
  CONSTRAINT `fk_recursos_has_projetos_recursos1`
    FOREIGN KEY (`id_recurso`)
    REFERENCES `sisgp`.`recursos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recursos_has_projetos_projetos1`
    FOREIGN KEY (`id_projeto`)
    REFERENCES `sisgp`.`projetos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisgp`.`projetos_gestores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`projetos_gestores` (
  `id_gestore` INT NOT NULL,
  `id_projeto` INT NOT NULL,
  `total_horas_previsto` INT NULL,
  `total_horas_efetivo` INT NULL,
  PRIMARY KEY (`id_gestore`, `id_projeto`),
  INDEX `fk_gestores_has_projetos_projetos1_idx` (`id_projeto` ASC),
  CONSTRAINT `fk_gestores_has_projetos_projetos1`
    FOREIGN KEY (`id_projeto`)
    REFERENCES `sisgp`.`projetos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisgp`.`status_contas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`status_contas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(255) NULL COMMENT 'paga. em aberto,cancelada',
  `st_ativo` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisgp`.`contas_pagar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`contas_pagar` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_cat_contas_pagar` INT NULL,
  `id_status_conta` INT NOT NULL,
  `dt_lancamento` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dt_vencimento` DATE NULL,
  `valor` DECIMAL(10,2) NULL,
  `nr_parcelas` INT NULL,
  `contrato` VARCHAR(255) NULL,
  `observacao` VARCHAR(255) NULL,
  `id_projeto` INT NULL,
  `st_ativo` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_contas_pagar_categoria_contas_pagar1_idx` (`id_cat_contas_pagar` ASC),
  INDEX `fk_contas_pagar_projetos1_idx` (`id_projeto` ASC),
  INDEX `fk_contas_pagar_status_contas1_idx` (`id_status_conta` ASC),
  CONSTRAINT `fk_contas_pagar_categoria_contas_pagar1`
    FOREIGN KEY (`id_cat_contas_pagar`)
    REFERENCES `sisgp`.`categoria_contas_pagar` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contas_pagar_projetos1`
    FOREIGN KEY (`id_projeto`)
    REFERENCES `sisgp`.`projetos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contas_pagar_status_contas1`
    FOREIGN KEY (`id_status_conta`)
    REFERENCES `sisgp`.`status_contas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisgp`.`contas_receber`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`contas_receber` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_cat_contas_receber` INT NULL,
  `id_status_conta` INT NOT NULL,
  `dt_lancamento` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dt_vencimento` DATE NULL,
  `valor` DECIMAL(10,2) NULL,
  `nr_parcelas` INT NULL,
  `contrato` VARCHAR(255) NULL,
  `observacao` VARCHAR(255) NULL,
  `id_projeto` INT NULL,
  `st_ativo` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_contas_receber_categoria_contas_receber1_idx` (`id_cat_contas_receber` ASC),
  INDEX `fk_contas_receber_projetos1_idx` (`id_projeto` ASC),
  INDEX `fk_contas_receber_status_contas1_idx` (`id_status_conta` ASC),
  CONSTRAINT `fk_contas_receber_categoria_contas_receber1`
    FOREIGN KEY (`id_cat_contas_receber`)
    REFERENCES `sisgp`.`categoria_contas_receber` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contas_receber_projetos1`
    FOREIGN KEY (`id_projeto`)
    REFERENCES `sisgp`.`projetos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contas_receber_status_contas1`
    FOREIGN KEY (`id_status_conta`)
    REFERENCES `sisgp`.`status_contas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sisgp`.`apontamento_horas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sisgp`.`apontamento_horas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_sub_atividade` INT NOT NULL,
  `nr_horas` INT NULL,
  `nr_horas_extras` INT NULL,
  `data` DATE NULL,
  `id_conta_pagar` INT NULL,
  `st_pago` TINYINT NULL,
  `st_ativo` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_controle_horas_efetivo_sub_atividades1_idx` (`id_sub_atividade` ASC),
  INDEX `fk_controle_horas_efetivo_contas_pagar1_idx` (`id_conta_pagar` ASC),
  CONSTRAINT `fk_controle_horas_efetivo_sub_atividades1`
    FOREIGN KEY (`id_sub_atividade`)
    REFERENCES `sisgp`.`sub_atividades` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_controle_horas_efetivo_contas_pagar1`
    FOREIGN KEY (`id_conta_pagar`)
    REFERENCES `sisgp`.`contas_pagar` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
