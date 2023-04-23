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
    categoryName varchar(30),
    descrip varchar(60),
    constraint PKCategory Primary Key(categoryId)
);

create table Coverage(
    coverageId varchar(30) not null,
    coverageName varchar(30),
    descrip varchar(60),
    cost double,
    percent double,
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

insert into Category (categoryId, categoryName, descrip) 
values ('Cat001', 'Responsabilidad Civil', 'testing 1'), 
('Cat002', 'Daño Directo', 'testing 2');

insert into Coverage (coverageId, coverageName, descrip, cost, percent, categoryId) 
values ('Cov001', 'Daño a Personas', 'testing cov 1', 2.00, 0.5, 'Cat001'), 
('Cov002', 'Daño a Bienes', 'testing cov 2', 2.05, 0.4, 'Cat001'),
('Cov003', 'Gastos Legales', 'testing cov 3', 2.00, 0.5, 'Cat001'),
('Cov004', 'Daño Directo', 'testing cov 4', 1.00, 0.3, 'Cat002'),
('Cov005', 'Daño al auto', 'testing cov 5', 2.00, 0.7, 'Cat002'),
('Cov006', 'Robo', 'testing cov 6', 3.50, 0.8, 'Cat002');