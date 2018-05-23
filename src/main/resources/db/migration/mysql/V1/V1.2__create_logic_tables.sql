CREATE TABLE `customer` (
  `id`                 INTEGER PRIMARY KEY AUTO_INCREMENT,
  `name`               VARCHAR(255) NOT NULL,
  `phone`              VARCHAR(11)  NOT NULL,
  `email`              VARCHAR(255) NOT NULL,
  `age`                INTEGER      NOT NULL,
  `driving_experience` INTEGER      NOT NULL
);

CREATE TABLE `order_status` (
  `id`   INTEGER PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL
);

CREATE TABLE `orders` (
  `id`         INTEGER PRIMARY KEY AUTO_INCREMENT,
  `date_start` DATETIME NOT NULL,
  `date_end`   DATETIME NOT NULL,
  `customer`   INTEGER  NOT NULL,
  `status`     INTEGER  NOT NULL,
  `car`        INTEGER  NOT NULL
);

ALTER TABLE `orders`
  ADD CONSTRAINT `fk_orders__car` FOREIGN KEY (`car`) REFERENCES `car` (`id`);

ALTER TABLE `orders`
  ADD CONSTRAINT `fk_orders__customer` FOREIGN KEY (`customer`) REFERENCES `customer` (`id`);

ALTER TABLE `orders`
  ADD CONSTRAINT `fk_orders__status` FOREIGN KEY (`status`) REFERENCES `order_status` (`id`);

CREATE TABLE `payment` (
  `id`          INTEGER PRIMARY KEY AUTO_INCREMENT,
  `status`      BOOLEAN  NOT NULL,
  `price`       INTEGER  NOT NULL,
  `date_create` DATETIME NOT NULL,
  `order`       INTEGER  NOT NULL
);

ALTER TABLE `payment`
  ADD CONSTRAINT `fk_payment__order` FOREIGN KEY (`order`) REFERENCES `orders` (`id`);

CREATE TABLE `service` (
  `id`          INTEGER PRIMARY KEY AUTO_INCREMENT,
  `name`        VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `price`       INTEGER      NOT NULL
);

CREATE TABLE `order_service` (
  `id`      INTEGER PRIMARY KEY AUTO_INCREMENT,
  `service` INTEGER NOT NULL,
  `order`   INTEGER NOT NULL
);

ALTER TABLE `order_service`
  ADD CONSTRAINT `fk_order_service__order` FOREIGN KEY (`order`) REFERENCES `orders` (`id`);

ALTER TABLE `order_service`
  ADD CONSTRAINT `fk_order_service__service` FOREIGN KEY (`service`) REFERENCES `service` (`id`)