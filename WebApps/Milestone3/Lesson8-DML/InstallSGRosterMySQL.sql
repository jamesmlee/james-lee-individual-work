-- Drop the database if it exists, as this is purely a sample database
DROP DATABASE IF EXISTS SGRoster;

-- Create a new database for our SGRoster example
CREATE DATABASE SGRoster;

-- Switch to the database
-- Note that SQL commands are not case-sensitive 
use SGRoster;

-- Create a table of our cohorts
CREATE TABLE Cohort
(CohortID int NOT NULL auto_increment,
StartDate date NOT NULL,
Subject varchar(50) NOT NULL,
Location varchar(50) NOT NULL,
PRIMARY KEY(CohortID));

-- Create a table of our apprentices
CREATE TABLE Apprentice
(ApprenticeID int NOT NULL auto_increment,
FirstName varchar(50) NOT NULL,
LastName varchar(50) NOT NULL,
PRIMARY KEY(ApprenticeID));

-- Create the bridge table
CREATE TABLE ApprenticeCohort
(ApprenticeID int NOT NULL,
CohortID int NOT NULL);

-- Create the foreign key relationships
ALTER TABLE ApprenticeCohort ADD CONSTRAINT fk_ApprenticeCohort_Apprentice FOREIGN KEY (ApprenticeID) REFERENCES Apprentice(ApprenticeID);
ALTER TABLE ApprenticeCohort ADD CONSTRAINT fk_ApprenticeCohort_Cohort FOREIGN KEY (CohortID) REFERENCES Cohort(CohortID);