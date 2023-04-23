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
    yearV int,
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
    insuredValue double,
    constraint PKPolicy Primary Key(policyId),
    constraint Foreign Key (vehicleLicensePlate) references Vehicle(licensePlate),
    constraint Foreign Key (userId) references Users(userId)
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

insert into Users (userId, pass, nameU, cellphone, email, cardNumber, typeU) values ('111','111','Diego','11111111', 'diego@testing.com','111',0),
('222','222','Jorge','22222222','jorge@testing.com','222',1), 
('333','333','Luis','33333333','luis@testing.com','333',0);

insert into Vehicle (licensePlate, brand, model, yearV) values ('ABC123', 'Toyota', 'Tercel', 1996), ('LRD596', 'LandRover', 'RangeRover', 2023);

insert into policyclass (policyId, userId, vehicleLicensePlate, term, initialDate, insuredValue) values('1', '111', 'ABC123', 'QUARTERLY', '2023-04-23', 3000.5),
('2', '333', 'LRD596', 'BIANNUAL', '2023-04-21', 2000000.0);