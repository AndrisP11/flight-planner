--liquibase formatted sql

--changeset andris:1

CREATE TABLE airport
(
    airport_id INT PRIMARY KEY,
    airport    VARCHAR(255) NOT NULL,
    country    VARCHAR(255) NOT NULL,
    city       VARCHAR(255) NOT NULL
);

CREATE TABLE flight
(
    id           INT PRIMARY KEY,
    airport_to   INT          NOT NULL,
    airport_from INT          NOT NULL,
    FOREIGN KEY (airport_to) REFERENCES airport (airport_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (airport_from) REFERENCES airport (airport_id) ON DELETE CASCADE ON UPDATE CASCADE,
    carrier      VARCHAR(255) NOT NULL,
    departure    TIMESTAMP    NOT NULL,
    arrival      TIMESTAMP    NOT NULL
);