USE sgroster;

/*
INSERT INTO Apprentice (FirstName, LastName)
VALUES ('Bob', 'Jones'),
('Brenda', 'Walters'),
('Shauna', 'Mullins');
*/

/*
SELECT * FROM Apprentice;
*/

/*
INSERT INTO Cohort (StartDate, Subject, Location)
VALUES ('2017/1/9', 'C#', 'Akron');
*/

/*
Try to add an ApprenticeCohort record with an invalid CohortID of 2
*/

/*
INSERT INTO ApprenticeCohort(ApprenticeID, CohortID)
VALUES(1, 2); 
*/

/*
Since a cohort with id 2 does not exist, the database will reject the query.
Tried to insert a value that violated the fk_ApprenticeCohort_Cohort constraint. 
Change the CohortID value from a 2 to a 1 and the query will work properly.
*/

/*
INSERT INTO ApprenticeCohort(ApprenticeID, CohortID)
VALUES(1, 1); 
*/

/*
Bob Jones
*/

/*
This is one of the ways relational databases protect you from creating bad data. 
ApprenticeCohort table requires a CohortID and the FK constraint requires that 
CohortID exist in the Cohort table. This is called referential integrity.
*/

/*
Create a few more cohorts and assign the remaining apprentices to them
*/

/*
INSERT INTO Cohort (StartDate, `Subject`, `Location`)
VALUES ('2017/1/9', 'Java', 'Akron');
*/

/*
INSERT INTO ApprenticeCohort(ApprenticeID, CohortID)
VALUES (2, 1), 
(3, 2);
*/

/*
Brenda Walters ... Shauna Mullins
*/

/*
move Bob Jones to another cohort 
*/

/*
UPDATE ApprenticeCohort
SET CohortID = 2
WHERE ApprenticeID = 1;
*/

/*
Let’s say that we want to cancel the Akron Java cohort. Try to delete CohortID 2
*/

/*
DELETE FROM Cohort
WHERE CohortID = 2;
*/

/*
Above fails because someone is enrolled in that cohort. Referential integrity.
*/

/*
-- move all cohort 2 to cohort 1
UPDATE ApprenticeCohort
SET CohortID = 1 WHERE CohortID = 2;
 
-- now delete cohort 2
DELETE FROM Cohort WHERE CohortID = 2;
*/