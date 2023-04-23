CREATE DATABASE Insurance;

USE Insurance;

create table Users (
    userId varchar(30) not null,
    pass varchar(15) not null,
    nameU varchar(30),
    cellphone varchar(8),
    email varchar(30),
    cardNumber varchar(16),
    typeU boolean,
    constraint PKUser Primary Key(userId),
    constraint UniqueCell UNIQUE(cellphone),
    constraint UniqueCard UNIQUE(cardNumber)
);

create table Vehicle (
    licensePlate varchar(6) not null,
    brand varchar(15),
    model varchar(15),
    yearV year,
    constraint PKVehicle Primary Key(licensePlate)
);

create table Category(
    categoryId varchar(30) not null,
    descrip varchar(60),
<<<<<<< HEAD
    constraint PKCategory Primary Key(categoryId);
=======
    Primary Key(id)
>>>>>>> 0e2f78d4115a7b16a74c1bc1ab99b5f2d57d309b
);

create table Coverage(
    coverageId varchar(30) not null,
    descrip varchar(60),
    cost double,
    percent double,
    categoryId varchar(30),
    constraint PKCoverage Primary Key(coverageId),
    constraint Foreign key (categoryId) references Category(id)
);

create table PolicyClass (
    policyId varchar(30) not null,
    userId varchar(30) not null,
    vehicleLicensePlate varchar(6) not null,
    term ENUM('QUARTERLY','BIANNUAL','ANNUAL'),
    initialDate date,
    insuredValue float,
    constraint PKPolicy Primary Key(policyId)
);

create table Applies (
    policyId varchar(30),
    coverageId varchar(30),
    constraint PKApplies Primary Key (policyId, coverageId),
    constraint Foreign Key (policyId) references PolicyClass(policyId),
    constraint Foreign Key (coverageId) references Coverage(coverageId)
);

