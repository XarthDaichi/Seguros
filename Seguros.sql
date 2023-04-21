CREATE DATABASE Insurance;

USE Insurance;

<<<<<<< HEAD
create table Usuario (
    id varchar(10) not null;
    clave varchar(10),
    nombre varchar(30),
    telefono varchar(8),
    correo varchar(30),
    tarjeta varchar(16),
    tipo integer,
    Primary Key(cedula)
=======
create table User (
    userId varchar(30) not null;
    pass varchar(15) not null;
    nameU varchar(30),
    cellphone varchar(8),
    email varchar(30),
    cardNumber varchar(16),
    typeU boolean,
    constraint PKUser Primary Key(userId),
    constraint UniqueCell UNIQUE(cellphone),
    constraint UniqueCard UNIQUE(cardNumber)
>>>>>>> data_creation
);

create table Vehicle (
    licensePlate varchar(6) not null,
    brand varchar(15),
    model varchar(15),
    yearV year,
    constraint PKVehicle Primary Key(licensePlate)
);

create table Category(
    id varchar(30) not null,
    descrip varchar(60),

);

create table Coverage(
    coverageId varchar(30) not null,
    descrip varchar(60),
    cost float,
    percent float,
    categoryId varchar(30),
    constraint PKCoverage Primary Key(coverageId),
    constraint Foreign key (categoryId) references Category(categoryId)
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

