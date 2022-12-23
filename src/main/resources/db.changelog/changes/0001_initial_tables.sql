--liquibase formatted sql

--changeset andris:1

CREATE TABLE airport
(
    airport VARCHAR(255) PRIMARY KEY NOT NULL,
    country VARCHAR(255)             NOT NULL,
    city    VARCHAR(255)             NOT NULL
);

CREATE TABLE flight
(
    id           LONG PRIMARY KEY,
    airport_to   VARCHAR(255) ,
    airport_from VARCHAR(255) ,
    FOREIGN KEY (airport_to) REFERENCES airport (airport)  ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (airport_from) REFERENCES airport (airport) ON DELETE SET NULL ON UPDATE CASCADE,
    carrier      VARCHAR(255) NOT NULL,
    departure    TIMESTAMP    NOT NULL,
    arrival      TIMESTAMP    NOT NULL
);