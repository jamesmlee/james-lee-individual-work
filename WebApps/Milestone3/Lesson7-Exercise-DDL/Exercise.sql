CREATE DATABASE MovieCatalogueMine;

USE MovieCatalogueMine;

CREATE TABLE IF NOT EXISTS Genre
(
	GenreID INT NOT NULL auto_increment,
    GenreName VARCHAR(30) NOT NULL,
    PRIMARY KEY (GenreID)
);

CREATE TABLE IF NOT EXISTS Director
(
	DirectorID INT NOT NULL auto_increment,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    BirthDate DATE NULL,
    PRIMARY KEY (DirectorID)
);

CREATE TABLE IF NOT EXISTS Rating
(
	RatingID INT NOT NULL auto_increment,
    RatingName VARCHAR(5) NOT NULL,
    PRIMARY KEY (RatingID)
);

CREATE TABLE IF NOT EXISTS Movie
(
   MovieID INT NOT NULL auto_increment,
   GenreID INT NOT NULL,
   DirectorID INT NULL,
   RatingID INT NULL,
   Title VARCHAR(128) NOT NULL,
   ReleaseDate DATE NULL,
   PRIMARY KEY (MovieID),
   FOREIGN KEY(GenreID) REFERENCES Genre(GenreID),
   FOREIGN KEY(DirectorID) REFERENCES Director(DirectorID),
   FOREIGN KEY(RatingID) REFERENCES Rating(RatingID)
);

CREATE TABLE IF NOT EXISTS Actor
(
	ActorID INT NOT NULL auto_increment,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    BirthDate DATE NULL,
    PRIMARY KEY (ActorID)
);

CREATE TABLE IF NOT EXISTS CastMembers
(
	CastMemberID INT NOT NULL auto_increment,
	ActorID INT NOT NULL,
    MovieID INT NOT NULL,
    Role VARCHAR(50) NOT NULL,
    PRIMARY KEY (CastMemberID),
    FOREIGN KEY(ActorID) REFERENCES Actor(ActorID),
    FOREIGN KEY(MovieID) REFERENCES Movie(MovieID)
);