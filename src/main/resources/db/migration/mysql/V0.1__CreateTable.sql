CREATE DATABASE IF NOT EXISTS fairyhr;

CREATE USER IF NOT EXISTS 'admin'@'localhost' IDENTIFIED BY 'admin';

GRANT ALL ON fairyhr.* to 'admin'@'localhost';

USE fairyhr;

CREATE TABLE User
(
    id char(10) primary key ,
    name varchar (30) not null,
    password varchar (30) not null,
    phoneNumber varchar (20) not null,
    residentId  varchar (20) not null,
    emailAddr varchar (50) not null,
    address varchar (200) not null,
    position varchar (50) not null
) DEFAULT CHARSET=utf8;
