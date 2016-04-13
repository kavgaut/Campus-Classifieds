CREATE TABLE NewUser(
UserId varchar2(10) primary key,
FirstName VARCHAR2(20),
LastName VARCHAR2(30),
EmailId VARCHAR2(25),
PhoneNo INTEGER,
UserPassword VARCHAR(15));

Create table Buy(
PostId INTEGER Primary Key,
UserId VARCHAR2(20) NOT NULL,
BCategory Varchar2(30) NOT NULL,
NeedBy Varchar2(30),
PriceExpected Float,
ProductName Varchar2(50) NOT NULL,
PhoneNo VARCHAR2(15),
Description Varchar2(1500) NOT NULL,
FOREIGN KEY (UserId)
REFERENCES NewUser
);

CREATE TABLE Rentals(
RId INTEGER PRIMARY KEY,
UserId varchar2(10),
RCategory varchar2(25) NOT NULL,
PropertyType varchar2(20) NOT NULL,
RPrice float NOT null,
NoOfBeds INTEGER,
NoOfBath float,
ALaundry CHAR(1),
AGym CHAR(1),
APool CHAR(1),
APetAllowed CHAR(1),
AParking CHAR(1),
AFurnished CHAR(1),
PhoneNo INTEGER,
DESCRIPTION VARCHAR2(1500),
StreetAddress varchar2(50),
CITY VARCHAR2(30) NOT NULL,
ZipCOde INTEGER,
FOREIGN KEY(UserId) references NewUser
);


CREATE TABLE SELLER(
SELLId INTEGER PRIMARY KEY,
UserId varchar2(10) not null,
SCategory varchar2(20) NOT NULL,
SPRODTITLE varchar2(40) NOT NULL,
SPrice float NOT null,
SCONDITION VARCHAR2(20) NOT NULL,
SMAKE VARCHAR2(50) NOT NULL,
SMODEL VARCHAR2(40),
SSIZE VARCHAR2(40),
CONTACTNO VARCHAR2(15),
DESCRIPTION VARCHAR2(1500),
FOREIGN KEY(UserId) references NewUser
);