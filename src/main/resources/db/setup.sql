-- create the databases
CREATE DATABASE IF NOT EXISTS fakebank;
USE fakebank;

DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS account
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    owner_id BIGINT       NOT NULL,
    balance  DOUBLE       NOT NULL,
    currency VARCHAR(255) NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES user (id),
    PRIMARY KEY (id)
);

INSERT INTO user (first_name, last_name)
    VALUE ('Ian Robert', 'O\'Brien');
INSERT INTO user (first_name, last_name)
    VALUE ('Jenny Wold', 'O\'Brien');

INSERT INTO account (balance, currency, owner_id)
VALUES (100, 'GBP', 1);
INSERT INTO account (balance, currency, owner_id)
VALUES (200, 'GBP', 2);
