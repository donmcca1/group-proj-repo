DROP DATABASE group_project_ericsson;

CREATE DATABASE group_project_ericsson;

USE group_project_ericsson;



-- *** USER TABLE ***
CREATE TABLE `group_project_ericsson`.`users` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE users;

-- *** BASE_DATE TABLE ***

CREATE TABLE `group_project_ericsson`.`base_data` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Date/Time` VARCHAR(45) NOT NULL,
  `Event_ID` INT NOT NULL,
  `Failure_Class` INT NULL,
  `UE_Type` INT NOT NULL,
  `Market` INT NOT NULL,
  `Operator` INT NOT NULL,
  `CellID` INT NOT NULL,
  `Duration` INT NOT NULL,
  `Cause_Code` INT NULL,
  `NE_Version` VARCHAR(10) NOT NULL,
  `IMSI` BIGINT NOT NULL,
  PRIMARY KEY (`id`));
 
-- Import Base Data: 
LOAD DATA LOCAL INFILE 'C:/base_data.csv'
INTO TABLE base_data
	FIELDS TERMINATED BY ','
    IGNORE 1 LINES
    (@`Date/Time`, Event_Id, @Failure_Class, UE_Type, Market, 
    Operator, CellID, Duration, @Cause_Code, NE_Version, IMSI)
   
    SET
    Failure_Class=REPLACE(@Failure_Class,'(null)',-1),
    Failure_Class=NULLIF(Failure_Class,-1),
    Cause_Code=REPLACE(@Cause_Code,'(null)',-1),
    Cause_Code=NULLIF(Cause_Code,-1),
    `Date/Time`=STR_TO_DATE(@`Date/Time`,'%d/%m/%Y %H:%i')  
    ;

SELECT * FROM base_data;
DROP TABLE base_date;

-- *** EVENT_CAUSE TABLE ***

CREATE TABLE `group_project_ericsson`.`event_cause` (
  `Cause_Code` INT NOT NULL,
  `Event_ID` INT NOT NULL,
  `Description` VARCHAR(100) NULL,
  PRIMARY KEY (`Cause_Code`, `Event_ID`));
  
-- Import Event Cause data: 
LOAD DATA LOCAL INFILE 'C:/event_cause.csv'
INTO TABLE event_cause
	FIELDS TERMINATED BY ','
    IGNORE 1 LINES
    (`Cause_Code`,`Event_ID`,@`Description`)
   
    SET
    `Description`=REPLACE(@`Description`,'(null)',-1),
    `Description`=NULLIF(`Description`,-1)
    ;
    
SELECT * FROM event_cause;
DROP TABLE event_cause;

-- *** FAILURE_CLASS TABLE ***

CREATE TABLE `group_project_ericsson`.`failure_class` (
  `Failure_Class` INT NOT NULL,
  `Description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Failure_Class`));
  
-- Import Failure Class data: 
LOAD DATA LOCAL INFILE 'C:/failure_class.csv'
INTO TABLE failure_class
	FIELDS TERMINATED BY ','
    IGNORE 1 LINES
    (Failure_Class, Description);
    
SELECT * FROM failure_class;
DROP TABLE failure_class;
    
-- *** UE TABLE ***

CREATE TABLE `group_project_ericsson`.`ue` (
  `TAC` INT NOT NULL,
  `Marketing_Name` VARCHAR(45) NOT NULL,
  `Manufacturer` VARCHAR(45) NOT NULL,
  `Model` VARCHAR(45) NOT NULL,
  `Vendor_Name` VARCHAR(45) NOT NULL,
  `UE_Type` VARCHAR(45) NULL,
  `OS` VARCHAR(45) NULL,
  `Input_Mode` VARCHAR(45) NULL,
  PRIMARY KEY (`TAC`));
  
-- Import UE data: 
LOAD DATA LOCAL INFILE 'C:/ue.txt'
INTO TABLE ue
	FIELDS TERMINATED BY '\t'
    IGNORE 1 LINES
    (TAC,Marketing_Name,Manufacturer,@Access_Capability,
    Model,Vendor_Name,@UE_Type,@OS,@Input_Mode)
   
    SET
    UE_Type=REPLACE(@UE_Type,'(null)',-1),
    UE_Type=NULLIF(UE_Type,-1),
    OS=REPLACE(@OS,'(null)',-1),
    OS=NULLIF(OS,-1),
    Input_Mode=REPLACE(@Input_Mode,'(null)',-1),
    Input_Mode=NULLIF(Input_Mode,-1)
    ;
    
SELECT * FROM ue;
DROP TABLE ue;

-- *** ACCESS_CAPABILITY TABLE *** 

CREATE TABLE `group_project_ericsson`.`access_capability` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `TAC` INT NOT NULL,
 `Access_Capability` VARCHAR(45) NOT NULL,
 PRIMARY KEY (`id`));
 
 DROP TABLE access_capability;
 
-- NEED TO ADD DATA IMPORT BIT 

-- *** MCC_MNC TABLE ***

CREATE TABLE `group_project_ericsson`.`mcc_mnc` (
  `MCC` INT NOT NULL,
  `MNC` INT NOT NULL,
  `Country` VARCHAR(45) NOT NULL,
  `Operator` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`MCC`, `MNC`));
  
-- Import MCC-MNC data: 
LOAD DATA LOCAL INFILE 'C:/mcc_mnc.csv'
INTO TABLE `mcc_mnc`
	FIELDS TERMINATED BY ','
    IGNORE 1 LINES
    (MCC,MNC,Country,Operator);
    
SELECT * FROM mcc_mnc;
DROP TABLE mcc_mnc;    
 