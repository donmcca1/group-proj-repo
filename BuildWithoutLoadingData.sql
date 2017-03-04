DROP DATABASE group_project_ericsson;

CREATE DATABASE group_project_ericsson;

USE group_project_ericsson;

CREATE TABLE `group_project_ericsson`.`users` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `group_project_ericsson`.`base_data` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_time` DATE NOT NULL,
  `event_id` INT NOT NULL,
  `failure_class` INT NULL,
  `ue_type` INT NOT NULL,
  `market` INT NOT NULL,
  `operator` INT NOT NULL,
  `cell_id` INT NOT NULL,
  `duration` INT NOT NULL,
  `cause_code` INT NULL,
  `ne_version` VARCHAR(10) NOT NULL,
  `imsi` BIGINT NOT NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `group_project_ericsson`.`event_cause` (
  `cause_code` INT NOT NULL,
  `event_id` INT NOT NULL,
  `description` VARCHAR(100) NULL,
  PRIMARY KEY (`cause_code`, `event_id`));
  
  CREATE TABLE `group_project_ericsson`.`failure_class` (
  `failure_class` INT NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`failure_class`));
  
  CREATE TABLE `group_project_ericsson`.`ue` (
  `tac` INT NOT NULL,
  `marketing_name` VARCHAR(45) NOT NULL,
  `manufacturer` VARCHAR(45) NOT NULL,
  `model` VARCHAR(45) NOT NULL,
  `vendor_name` VARCHAR(45) NOT NULL,
  `ue_type` VARCHAR(45) NULL,
  `os` VARCHAR(45) NULL,
  `input_mode` VARCHAR(45) NULL,
  PRIMARY KEY (`tac`));
  
  CREATE TABLE `group_project_ericsson`.`access_capability` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `tac` INT NOT NULL,
 `access_capability` VARCHAR(45) NOT NULL,
 PRIMARY KEY (`id`));
 
 CREATE TABLE `group_project_ericsson`.`mcc_mnc` (
  `mcc` INT NOT NULL,
  `mnc` INT NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `operator` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`mcc`, `mnc`));
  
  SELECT * FROM base_data;
  SELECT * FROM event_cause;
  SELECT * FROM failure_class;
  SELECT * FROM ue;
  SELECT * FROM mcc_mnc;
  
