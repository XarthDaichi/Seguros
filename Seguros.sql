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
    categoryId int not null AUTO_INCREMENT,
    categoryName varchar(30),
    descrip varchar(60),
    constraint PKCategory Primary Key(categoryId)
);

create table Coverage(
    coverageId int not null AUTO_INCREMENT,
    coverageName varchar(30),
    descrip varchar(60),
    cost double,
    percent double,
    categoryId int,
    constraint PKCoverage Primary Key(coverageId),
    constraint Foreign key (categoryId) references Category(categoryId)
);

create table PolicyClass (
    policyId int not null AUTO_INCREMENT,
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
    policyId int,
    coverageId int,
    constraint PKApplies Primary Key (policyId, coverageId),
    constraint Foreign Key (policyId) references PolicyClass(policyId),
    constraint Foreign Key (coverageId) references Coverage(coverageId)
);

insert into Category (categoryName, descrip) 
values ('Responsabilidad Civil', 'testing 1'), 
('Daño Directo', 'testing 2');

insert into Coverage (coverageName, descrip, cost, percent, categoryId) 
values ('Daño a Personas', 'testing cov 1', 2.00, 0.5, 1), 
('Daño a Bienes', 'testing cov 2', 2.05, 0.4, 1),
('Gastos Legales', 'testing cov 3', 2.00, 0.5, 1),
('Daño Directo', 'testing cov 4', 1.00, 0.3, 2),
('Daño al auto', 'testing cov 5', 2.00, 0.7, 2),
('Robo', 'testing cov 6', 3.50, 0.8, 2);

insert into Users (userId, pass, nameU, cellphone, email, cardNumber, typeU) values ('111','111','Diego','11111111', 'diego@testing.com','111',0),
('222','222','Jorge','22222222','jorge@testing.com','222',1), 
('333','333','Luis','33333333','luis@testing.com','333',0);

insert into Vehicle (licensePlate, brand, model, yearV) values ('ABC123', 'Toyota', 'Tercel', 1996), ('LRD596', 'LandRover', 'RangeRover', 2023);

insert into policyclass (userId, vehicleLicensePlate, term, initialDate, insuredValue) values('111', 'ABC123', 'QUARTERLY', '2023-04-23', 3000.5),
('333', 'LRD596', 'BIANNUAL', '2023-04-21', 2000000.0);