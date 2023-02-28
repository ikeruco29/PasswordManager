CREATE DATABASE IF NOT EXISTS PasswordManager;
USE PasswordManager;

DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS redes;

CREATE TABLE usuarios (
    userId int(32) NOT NULL AUTO_INCREMENT,
    mail varchar(255),
    password varchar(255),
    nombre varchar(255),
    apellidos varchar(255),
    PRIMARY KEY (userId)
);

CREATE TABLE redes (
    redId int(32) NOT NULL AUTO_INCREMENT,
    userId int(32),
    mail varchar(255),
    password varchar(255),
    nom_red varchar(255),
    PRIMARY KEY (redId)
);