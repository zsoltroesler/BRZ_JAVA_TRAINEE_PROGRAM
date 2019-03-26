CREATE DATABASE	IF NOT EXISTS inventurdb DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_german2_ci;

DROP TABLE IF EXISTS lieferant;

CREATE TABLE inventurdb.lieferant (
  lid int NOT NULL AUTO_INCREMENT,
  lieferantId varchar(12) NOT NULL,
  lieferantName varchar(50) NOT NULL,
  lieferantEmail varchar(50),
  lieferantTel varchar(50),
  PRIMARY KEY (lid),
  UNIQUE (lieferantId),
  INDEX (lieferantName)
);

DROP TABLE IF EXISTS produkt;

CREATE TABLE inventurdb.produkt (
  pid int NOT NULL AUTO_INCREMENT,
  lid int,
  produktId varchar(12) NOT NULL,
  produktName varchar(50) NOT NULL,
  produktPreis int(5),
  produktLieferant varchar(50) NOT NULL,
  produktAnzahl int(5),
  PRIMARY KEY (pid),
  UNIQUE (produktId),
  INDEX (produktId),
  INDEX (produktName),
  FOREIGN KEY (lid) REFERENCES lieferant(lid)
  ON DELETE CASCADE
);
