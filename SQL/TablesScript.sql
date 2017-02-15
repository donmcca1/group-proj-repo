DROP DATABASE group_project_ericsson;

CREATE DATABASE group_project_ericsson;

USE group_project_ericsson;

-- ******************
-- *** USER TABLE ***
-- ******************

CREATE TABLE `group_project_ericsson`.`users` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE users;

-- ***********************
-- *** BASE_DATE TABLE ***
-- ***********************

CREATE TABLE `group_project_ericsson`.`base_data` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_time` VARCHAR(45) NOT NULL,
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
 
-- Import Base Data: 
LOAD DATA LOCAL INFILE 'C:/base_data.csv'
INTO TABLE base_data
	FIELDS TERMINATED BY ','
    IGNORE 1 LINES
    (@date_time, event_id, @failure_class, ue_type, market, 
    operator, cell_id, duration, @cause_code, ne_version, imsi)
   
    SET
    failure_class=REPLACE(@failure_class,'(null)',-1),
    failure_class=NULLIF(failure_class,-1),
    cause_code=REPLACE(@cause_code,'(null)',-1),
    cause_code=NULLIF(cause_code,-1),
    `date_time`=STR_TO_DATE(@`date_time`,'%d/%m/%Y %H:%i')  
    ;

SELECT * FROM base_data;
DROP TABLE base_date;

-- *************************
-- *** EVENT_CAUSE TABLE ***
-- *************************

CREATE TABLE `group_project_ericsson`.`event_cause` (
  `cause_code` INT NOT NULL,
  `event_id` INT NOT NULL,
  `description` VARCHAR(100) NULL,
  PRIMARY KEY (`cause_code`, `event_id`));
  
-- Import Event Cause data: 
LOAD DATA LOCAL INFILE 'C:/event_cause.csv'
INTO TABLE event_cause
	FIELDS TERMINATED BY ','
    IGNORE 1 LINES
    (`cause_code`,`event_id`,@`description`)
   
    SET
    `description`=REPLACE(@`description`,'(null)',-1),
    `description`=NULLIF(`description`,-1)
    ;
    
SELECT * FROM event_cause;
DROP TABLE event_cause;

-- ***************************
-- *** FAILURE_CLASS TABLE ***
-- ***************************

CREATE TABLE `group_project_ericsson`.`failure_class` (
  `failure_class` INT NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`failure_class`));
  
-- Import Failure Class data: 
LOAD DATA LOCAL INFILE 'C:/failure_class.csv'
INTO TABLE failure_class
	FIELDS TERMINATED BY ','
    IGNORE 1 LINES
    (failure_class, description);
    
SELECT * FROM failure_class;
DROP TABLE failure_class;
    
-- ****************
-- *** UE TABLE ***
-- ****************

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
  
-- Import UE data: 
LOAD DATA LOCAL INFILE 'C:/ue.txt'
INTO TABLE ue
	FIELDS TERMINATED BY '\t'
    IGNORE 1 LINES
    (tac,marketing_name,manufacturer,@access_capability,
    model,vendor_name,@ue_type,@OS,@input_mode)
   
    SET
    ue_type=REPLACE(@ue_type,'(null)',-1),
    ue_type=NULLIF(ue_type,-1),
    os=REPLACE(@os,'(null)',-1),
    os=NULLIF(os,-1),
    input_mode=REPLACE(@input_mode,'(null)',-1),
    input_mode=NULLIF(input_mode,-1)
    ;
    
SELECT * FROM ue;
DROP TABLE ue;

-- *******************************
-- *** ACCESS_CAPABILITY TABLE *** 
-- *******************************

CREATE TABLE `group_project_ericsson`.`access_capability` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `tac` INT NOT NULL,
 `access_capability` VARCHAR(45) NOT NULL,
 PRIMARY KEY (`id`));
 
 DROP TABLE access_capability;
 
-- NEED TO ADD DATA IMPORT BIT 

-- *********************
-- *** MCC_MNC TABLE ***
-- *********************

CREATE TABLE `group_project_ericsson`.`mcc_mnc` (
  `mcc` INT NOT NULL,
  `mnc` INT NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `operator` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`mcc`, `mnc`));
  
-- Import MCC-MNC data: 
LOAD DATA LOCAL INFILE 'C:/mcc_mnc.csv'
INTO TABLE `mcc_mnc`
	FIELDS TERMINATED BY ','
    IGNORE 1 LINES
    (mcc,mnc,country,operator);
    
SELECT * FROM mcc_mnc;
DROP TABLE mcc_mnc;    
 