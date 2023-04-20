CREATE DATABASE Seguros;

USE Seguros;

create table Usuario (
    cedula varchar(10) not null;
    clave varchar(10),
    nombre varchar(30),
    telefono varchar(8),
    correo varchar(30),
    tarjeta varchar(16),
    tipo integer,
    Primary Key(cedula)
);
