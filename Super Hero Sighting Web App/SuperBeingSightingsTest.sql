DROP DATABASE IF EXISTS SuperBeingSightingsTest;
CREATE DATABASE SuperBeingSightingsTest;
USE SuperBeingSightingsTest;

CREATE TABLE `SuperBeing` (
	`SuperBeingID` 	INT NOT NULL AUTO_INCREMENT,
    `Name` 			VARCHAR(50) NOT NULL,
    `Alias` 		VARCHAR(50) NULL,
    `Description` 	TEXT NULL,
	PRIMARY KEY (`SuperBeingID`)
);

CREATE TABLE `SuperPower` (
	`SuperPowerID` 	INT NOT NULL AUTO_INCREMENT,
    `Name`			VARCHAR(30) NOT NULL,
    `Description`	VARCHAR(255) NOT NULL,
    PRIMARY KEY (`SuperPowerID`)
);

CREATE TABLE `Address` (
	`AddressID`		INT NOT NULL AUTO_INCREMENT,
    `Street`		VARCHAR(50) NOT NULL,
    `City`			VARCHAR(50) NOT NULL,
    `State`			VARCHAR(50) NOT NULL,
    `Zip`			VARCHAR(5) NOT NULL,
    PRIMARY KEY (`AddressID`)
);

CREATE TABLE `Affiliation` (
	`AffiliationID`	INT NOT NULL AUTO_INCREMENT,
    `LeaderID`		INT NOT NULL,
    `AddressID`		INT NOT NULL,
    `Name`			VARCHAR(30),
    `Description`	TEXT NULL,
    PRIMARY KEY (`AffiliationID`)
);

CREATE TABLE `Location` (
	`LocationID`	INT NOT NULL AUTO_INCREMENT,
    `AddressID`		INT NOT NULL,
    `Name`			VARCHAR(30) NOT NULL,
    `Description`	TEXT NOT NULL,
    `Latitude`		DECIMAL(7,4) NOT NULL,
    `Longitude`		DECIMAL(7,4) NOT NULL,
    PRIMARY KEY (`LocationID`)
);

CREATE TABLE `Sighting` (
	`SightingID` 	INT NOT NULL AUTO_INCREMENT,
    `LocationID`	INT NOT NULL,
    `Date` 			DATE NOT NULL,
    `Time`			TIME NULL,
    `Description`	TEXT NULL,
    PRIMARY KEY (`SightingID`)    
);

ALTER TABLE `Affiliation` ADD CONSTRAINT fk_Affiliation_Leader
FOREIGN KEY (`LeaderID`) REFERENCES `SuperBeing` (`SuperBeingID`);

ALTER TABLE `Affiliation` ADD CONSTRAINT fk_Affiliation_Address
FOREIGN KEY (`AddressID`) REFERENCES `Address` (`AddressID`);

ALTER TABLE `Location` ADD CONSTRAINT fk_Location_Address
FOREIGN KEY (`AddressID`) REFERENCES `Address` (`AddressID`);

ALTER TABLE `Sighting` ADD CONSTRAINT fk_Sighting_Location
FOREIGN KEY (`LocationID`) REFERENCES `Location` (`LocationID`);

CREATE TABLE `SuperBeingSuperPower` (
	`SuperBeingID` 	INT NOT NULL,
    `SuperPowerID` 	INT NOT NULL,
    PRIMARY KEY (`SuperBeingID`,`SuperPowerID`)
);

CREATE TABLE `SuperBeingSighting` (
	`SuperBeingID`	INT NOT NULL,
    `SightingID`	INT NOT NULL,
    PRIMARY KEY (`SuperBeingID`,`SightingID`)
);

CREATE TABLE `SuperBeingAffiliation` (
	`SuperBeingID`	INT NOT NULL,
    `AffiliationID`	INT NOT NULL,
    PRIMARY KEY (`SuperBeingID`, `AffiliationID`)
);

ALTER TABLE `SuperBeingSuperPower` ADD CONSTRAINT `fk_SuperBeingSuperPower_SuperBeing`
FOREIGN KEY (`SuperBeingID`) REFERENCES `SuperBeing` (`SuperBeingID`);

ALTER TABLE `SuperBeingSuperPower` ADD CONSTRAINT `fk_SuperBeingSuperPower_SuperPower`
FOREIGN KEY (`SuperPowerID`) REFERENCES `SuperPower` (`SuperPowerID`);

ALTER TABLE `SuperBeingSighting` ADD CONSTRAINT `fk_SuperBeingSighting_SuperBeing`
FOREIGN KEY (`SuperBeingID`) REFERENCES `SuperBeing` (`SuperBeingID`);

ALTER TABLE `SuperBeingSighting` ADD CONSTRAINT `fk_SuperBeingSighting_Sighting`
FOREIGN KEY (`SightingID`) REFERENCES `Sighting` (`SightingID`);

ALTER TABLE `SuperBeingAffiliation` ADD CONSTRAINT `fk_SuperBeingAffiliation_SuperBeing`
FOREIGN KEY (`SuperBeingID`) REFERENCES `SuperBeing` (`SuperBeingID`);

ALTER TABLE `SuperBeingAffiliation` ADD CONSTRAINT `fk_SuperBeingAffiliation_Affiliation`
FOREIGN KEY (`AffiliationID`) REFERENCES `Affiliation` (`AffiliationID`);