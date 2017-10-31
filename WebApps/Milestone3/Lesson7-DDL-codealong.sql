/*
CREATE DATABASE SGRoster;
*/

USE SGRoster;

/*
CREATE TABLE Cohort
(
   CohortID INT NOT NULL auto_increment,
   StartDate DATE NOT NULL,
   Subject VARCHAR(30) NOT NULL,
   Location VARCHAR(30) NOT NULL,
   PRIMARY KEY (CohortID)
);

CREATE TABLE Apprentice 
(
   ApprenticeID INT NOT NULL auto_increment,
   FirstName VARCHAR(30) NOT NULL,
   LastName VARCHAR(30) NOT NULL,
   CohortID INT NOT NULL,
   PRIMARY KEY(ApprenticeID),
   FOREIGN KEY(CohortID) REFERENCES Cohort(CohortID)
);
*/

/* 
By putting the CohortID on the Apprentice table as a foreign key, we have limited 
an apprentice to only one cohort! Need to remove the CohortID from the Apprentice 
table and create another table to handle a many-to-many relationship between apprentices 
and cohorts. We can make changes to an existing table using the ALTER keyword.

Before we can remove the column, however, we must first remove the foreign key constraint.
*/

/*
ALTER TABLE Apprentice 
DROP FOREIGN KEY apprentice_ibfk_1;

ALTER TABLE Apprentice 
DROP COLUMN CohortID;
*/

/*
We made a bit of a mess of our table by adding a column that did not give us the relationship 
we wanted. If we wanted to start completely over, we could drop the whole table.
*/

/*
DROP TABLE Apprentice;
*/

/*
Modify your previous CREATE TABLE statement by removing the CohortID column and 
put the table back by executing the script.
*/

/*
CREATE TABLE Apprentice 
(
   ApprenticeID INT NOT NULL auto_increment,
   FirstName VARCHAR(30) NOT NULL,
   LastName VARCHAR(30) NOT NULL,
   PRIMARY KEY(ApprenticeID)
);
*/

/*
Step 5– Adding the ApprenticeCohort Bridge Table
*/

/*
CREATE TABLE ApprenticeCohort 
(
    ApprenticeID INT NOT NULL,
    CohortID INT NOT NULL,        
    PRIMARY KEY (ApprenticeID, CohortID)
);

ALTER TABLE ApprenticeCohort
ADD CONSTRAINT fk_ApprenticeCohort_Apprentice 
FOREIGN KEY (ApprenticeID) REFERENCES Apprentice(ApprenticeID);

ALTER TABLE ApprenticeCohort 
ADD CONSTRAINT fk_ApprenticeCohort_Cohort 
FOREIGN KEY (CohortID) REFERENCES Cohort(CohortID);
*/

/*
Just as we can drop columns on existing tables we can also add columns using the ALTER TABLE
command. Unlike in the drop case with ADD we do not need to specify the COLUMN keyword
*/

/*
ALTER TABLE Apprentice 
ADD DateOfBirth DATE not null;
*/

/*
Oops! We wanted to make that birthdate column nullable and be a DATETIME data type
*/

/*
ALTER TABLE Apprentice 
MODIFY COLUMN DateOfBirth DATETIME NULL;
*/