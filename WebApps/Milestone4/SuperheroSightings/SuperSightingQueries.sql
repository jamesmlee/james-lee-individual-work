Use supersightings_queries;

#1 getAllSuperPersonsBySightingLocation
SELECT `superperson`.`name`, `location`.`name` 
FROM `superperson`
INNER JOIN `superperson_sighting` 
ON `superperson`.`SuperPersonId` = `superperson_sighting`.`SuperPersonId`
INNER JOIN `sighting` 
ON `superperson_sighting`.`SightingId` = `sighting`.`SightingId`
INNER JOIN `location` 
ON `sighting`.`LocationId` = `location`.`LocationId`
WHERE `location`.`LocationId` = 1;

#2 getAllSuperPersonsByOrganization
SELECT `superperson`.`name` AS `Person Name`, `organization`.`name` 
FROM `superperson`
INNER JOIN `superperson_organization` 
ON `superperson`.`superpersonId` = `superperson_organization`.`superpersonId`
INNER JOIN `organization` 
ON `superperson_organization`.`OrganizationId` = `organization`.`OrganizationId`
WHERE `organization`.`organizationId` = 1;

#3 getAllSuperPersonsBySighting
SELECT `superperson`.`name` AS `Person Name`, `sighting`.`sightingid`, `sighting`.`date` 
FROM `superperson`
INNER JOIN `superperson_sighting` 
ON `superperson`.`SuperPersonId` = `superperson_sighting`.`SuperPersonId`
INNER JOIN `sighting` 
ON `superperson_sighting`.`SightingId` = `sighting`.`SightingId`
WHERE `sighting`.`sightingId` = 1;

#4 getAllSightingsByDate
SELECT `sighting`.`sightingid`, `sighting`.`Description`, `sighting`.`date` 
FROM `sighting`
WHERE `date`= 20170515;

#5 getAllLocationsBySuperPersonId
SELECT `superperson`.`Name` AS `Super Person`, `location`.`Name` AS `Location`
FROM `location`
INNER JOIN `sighting`
ON `location`.`LocationId` = `sighting`.`LocationId`
INNER JOIN `superperson_sighting`
ON `superperson_sighting`.`SightingId` = `sighting`.`SightingId`
INNER JOIN `superperson`
ON `superperson_sighting`.`SuperpersonId` = `superperson`.`SuperPersonId`
WHERE `superperson`.`SuperPersonId` = 5;

#6 getAllOrganizationsBySuperPerson
SELECT `organization`.`name` , `superperson`.`name` 
FROM `organization`
INNER JOIN `superperson_organization` 
ON `organization`.`organizationId` = `superperson_organization`.`organizationId`
INNER JOIN `superperson` 
ON `superperson_organization`.`superpersonId` = `superperson`.`superpersonId`
WHERE `superperson`.`superpersonid` = 5;