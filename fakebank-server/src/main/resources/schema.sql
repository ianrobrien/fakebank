CREATE TABLE IF NOT EXISTS owner
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
  FOREIGN KEY (owner_id) REFERENCES owner (id),
  PRIMARY KEY (id)
);
