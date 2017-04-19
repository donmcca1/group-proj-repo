DROP DATABASE group_project_ericsson_test;

CREATE DATABASE group_project_ericsson_test;

USE group_project_ericsson_test;

CREATE TABLE `group_project_ericsson_test`.`users` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `group_project_ericsson_test`.`failure_class` (
  `failure_class` INT NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`failure_class`));
  
CREATE TABLE `group_project_ericsson_test`.`event_cause` (
  `cause_code` INT NOT NULL,
  `event_id` INT NOT NULL,
  `description` VARCHAR(100) NULL,
  PRIMARY KEY (`cause_code`, `event_id`));
  
  CREATE TABLE `group_project_ericsson_test`.`mcc_mnc` (
  `mcc` INT NOT NULL,
  `mnc` INT NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `operator` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`mcc`, `mnc`));
  
  CREATE TABLE `group_project_ericsson_test`.`ue` (
  `tac` INT NOT NULL,
  `marketing_name` VARCHAR(45) NOT NULL,
  `manufacturer` VARCHAR(45) NOT NULL,
  `model` VARCHAR(45) NOT NULL,
  `vendor_name` VARCHAR(45) NOT NULL,
  `ue_type` VARCHAR(45) NULL,
  `os` VARCHAR(45) NULL,
  `input_mode` VARCHAR(45) NULL,
  PRIMARY KEY (`tac`));
  

CREATE TABLE `group_project_ericsson_test`.`base_data` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_time` DATE NOT NULL,
  `event_id` INT NOT NULL,
  `failure_class` INT NULL,
  `ue_type` INT NOT NULL,
  `market` INT NOT NULL,
  `operator` INT NOT NULL,
  `cell_id` INT NOT NULL,
  `duration` INT NOT NULL,
  `cause_code` INT NOT NULL,
  `ne_version` VARCHAR(10) NOT NULL,
  `imsi` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_failure_class` FOREIGN KEY (`failure_class`)
  REFERENCES `failure_class`(`failure_class`),
  CONSTRAINT `fk_event_cause` FOREIGN KEY (`cause_code`,`event_id`)
  REFERENCES `event_cause`(`cause_code`, `event_id`),
  CONSTRAINT `fk_mcc_mnc` FOREIGN KEY (`market`,`operator`)
  REFERENCES `mcc_mnc`(`mcc`, `mnc`),
  CONSTRAINT `fk_ue` FOREIGN KEY (`ue_type`)
  REFERENCES `ue`(`tac`)
  );
  
  CREATE TABLE `group_project_ericsson_test`.`access_capability` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `tac` INT NOT NULL,
 `access_capability` VARCHAR(45) NOT NULL,
 PRIMARY KEY (`id`));


-- INSERT INTO base_data ( `date_time`, `event_id`, `failure_class`, `ue_type`, `market`, `operator`, `cell_id`, `duration`, `cause_code`, `ne_version`, `imsi`)
--     VALUES ( );

INSERT INTO event_cause ( `cause_code`, `event_id`, `description` )
    VALUES ( 1, 1, "test event description" );

INSERT INTO failure_class ( `failure_class`, `description` )
    VALUES ( 1, "test failure description" );

INSERT INTO mcc_mnc ( `mcc`, `mnc`, `country`, `operator`)
    VALUES ( 1,1, "test mcc country", "test mnc operator" );

INSERT INTO ue ( `tac`, `marketing_name`, `manufacturer`, `model`, `vendor_name`,`ue_type`, `os`, `input_mode`)
    VALUES ( 1, "test ue marketing_name", "test ue manufacturer", "test ue model", "test ue vendor_name", "test ue ue_type", "test ue os", "test ue input_mode" );
  
  SELECT * FROM base_data;
  SELECT * FROM event_cause;
  SELECT * FROM failure_class;
  SELECT * FROM ue;
  SELECT * FROM mcc_mnc;
