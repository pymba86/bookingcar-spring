CREATE TABLE car_actuator (
  [id]   INTEGER PRIMARY KEY IDENTITY,
  [name] VARCHAR(255) NOT NULL
);

CREATE TABLE car_category (
  [id]                    INTEGER PRIMARY KEY IDENTITY,
  [name]                  VARCHAR(255) NOT NULL,
  [driver_age_min]        INTEGER      NOT NULL,
  [driver_experience_min] INTEGER      NOT NULL
);

CREATE TABLE car_fuel (
  [id]   INTEGER PRIMARY KEY IDENTITY,
  [name] VARCHAR(255) NOT NULL
);

CREATE TABLE car_gearbox (
  [id]   INTEGER PRIMARY KEY IDENTITY,
  [name] VARCHAR(255) NOT NULL
);

CREATE TABLE location (
  [id]      INTEGER PRIMARY KEY IDENTITY,
  [name]    VARCHAR(255) NOT NULL,
  [address] VARCHAR(255) NOT NULL,
  [phone]   VARCHAR(255) NOT NULL
);

CREATE TABLE car (
  [id]              INTEGER PRIMARY KEY IDENTITY,
  [name]            VARCHAR(255) NOT NULL,
  [production_year] INTEGER,
  [doors]           INTEGER,
  [places]          INTEGER,
  [motor_power]     INTEGER,
  [price]           INTEGER       NOT NULL,
  [location]        INTEGER,
  [fuel]            INTEGER,
  [category]        INTEGER,
  [actuator]        INTEGER,
  [gearbox]         INTEGER
);


ALTER TABLE [car]
  ADD CONSTRAINT [fk_car__actuator] FOREIGN KEY ([actuator]) REFERENCES car_actuator ([id]);

ALTER TABLE [car]
  ADD CONSTRAINT [fk_car__category] FOREIGN KEY ([category]) REFERENCES car_category ([id]);

ALTER TABLE [car]
  ADD CONSTRAINT [fk_car__fuel] FOREIGN KEY ([fuel]) REFERENCES car_fuel ([id]);

ALTER TABLE [car]
  ADD CONSTRAINT [fk_car__gearbox] FOREIGN KEY ([gearbox]) REFERENCES car_gearbox ([id]);

ALTER TABLE [car]
  ADD CONSTRAINT [fk_car__location] FOREIGN KEY ([location]) REFERENCES location ([id])

CREATE TABLE customer (
  [id]                 INTEGER PRIMARY KEY IDENTITY,
  [name]               VARCHAR(255) NOT NULL,
  [phone]              VARCHAR(11)  NOT NULL,
  [email]              VARCHAR(255) NOT NULL,
  [age]                INTEGER      NOT NULL,
  [driving_experience] INTEGER      NOT NULL
);

CREATE TABLE order_status (
  [id]   INTEGER PRIMARY KEY IDENTITY,
  [name] VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
  [id]         INTEGER PRIMARY KEY IDENTITY,
  [date_start] DATETIME2(0) NOT NULL,
  [date_end]   DATETIME2(0) NOT NULL,
  [customer]   INTEGER  NOT NULL,
  [status]     INTEGER  NOT NULL,
  [car]        INTEGER  NOT NULL
);

ALTER TABLE [orders]
  ADD CONSTRAINT [fk_orders__car] FOREIGN KEY ([car]) REFERENCES car ([id]);

ALTER TABLE [orders]
  ADD CONSTRAINT [fk_orders__customer] FOREIGN KEY ([customer]) REFERENCES customer ([id]);

ALTER TABLE [orders]
  ADD CONSTRAINT [fk_orders__status] FOREIGN KEY ([status]) REFERENCES order_status ([id]);

CREATE TABLE payment (
  [id]          INTEGER PRIMARY KEY IDENTITY,
  [status]      BIT  NOT NULL,
  [price]       INTEGER  NOT NULL,
  [date_create] DATETIME2(0) NOT NULL,
  [order]       INTEGER  NOT NULL
);

ALTER TABLE [payment]
  ADD CONSTRAINT [fk_payment__order] FOREIGN KEY ([order]) REFERENCES orders ([id]);

CREATE TABLE service (
  [id]          INTEGER PRIMARY KEY IDENTITY,
  [name]        VARCHAR(255) NOT NULL,
  [description] VARCHAR(255) NOT NULL,
  [price]       INTEGER      NOT NULL
);

CREATE TABLE order_service (
  [id]      INTEGER PRIMARY KEY IDENTITY,
  [service] INTEGER NOT NULL,
  [order]   INTEGER NOT NULL
);

ALTER TABLE [order_service]
  ADD CONSTRAINT [fk_order_service__order] FOREIGN KEY ([order]) REFERENCES orders ([id]);

ALTER TABLE [order_service]
  ADD CONSTRAINT [fk_order_service__service] FOREIGN KEY ([service]) REFERENCES service ([id])