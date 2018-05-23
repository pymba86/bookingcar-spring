CREATE SCHEMA IF NOT EXISTS bx;

CREATE TABLE `car_actuator` (
  `id`   INTEGER PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL
);

CREATE TABLE `car_category` (
  `id`                    INTEGER PRIMARY KEY AUTO_INCREMENT,
  `name`                  VARCHAR(255) NOT NULL,
  `driver_age_min`        INTEGER      NOT NULL,
  `driver_experience_min` INTEGER      NOT NULL
);

CREATE TABLE `car_fuel` (
  `id`   INTEGER PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL
);

CREATE TABLE `car_gearbox` (
  `id`   INTEGER PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL
);

CREATE TABLE `location` (
  `id`      INTEGER PRIMARY KEY AUTO_INCREMENT,
  `name`    VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `phone`   VARCHAR(255) NOT NULL
);

CREATE TABLE `car` (
  `id`              INTEGER PRIMARY KEY AUTO_INCREMENT,
  `name`            VARCHAR(255) NOT NULL,
  `production_year` INTEGER,
  `doors`           INTEGER,
  `places`          INTEGER,
  `motor_power`     INTEGER,
  `price`           INTEGER       NOT NULL,
  `location`        INTEGER,
  `fuel`            INTEGER,
  `category`        INTEGER,
  `actuator`        INTEGER,
  `gearbox`         INTEGER
);


ALTER TABLE `car`
  ADD CONSTRAINT `fk_car__actuator` FOREIGN KEY (`actuator`) REFERENCES `car_actuator` (`id`);

ALTER TABLE `car`
  ADD CONSTRAINT `fk_car__category` FOREIGN KEY (`category`) REFERENCES `car_category` (`id`);

ALTER TABLE `car`
  ADD CONSTRAINT `fk_car__fuel` FOREIGN KEY (`fuel`) REFERENCES `car_fuel` (`id`);

ALTER TABLE `car`
  ADD CONSTRAINT `fk_car__gearbox` FOREIGN KEY (`gearbox`) REFERENCES `car_gearbox` (`id`);

ALTER TABLE `car`
  ADD CONSTRAINT `fk_car__location` FOREIGN KEY (`location`) REFERENCES `location` (`id`)