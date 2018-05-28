CREATE TABLE "car_actuator" (
  "id"   SERIAL CONSTRAINT "pk_car_actuator" PRIMARY KEY,
  "name" TEXT NOT NULL
);

CREATE TABLE "car_category" (
  "id"                    SERIAL CONSTRAINT "pk_car_category" PRIMARY KEY,
  "name"                  TEXT    NOT NULL,
  "driver_age_min"        INTEGER NOT NULL,
  "driver_experience_min" INTEGER NOT NULL
);

CREATE TABLE "car_fuel" (
  "id"   SERIAL CONSTRAINT "pk_car_fuel" PRIMARY KEY,
  "name" TEXT NOT NULL
);

CREATE TABLE "car_gearbox" (
  "id"   SERIAL CONSTRAINT "pk_car_gearbox" PRIMARY KEY,
  "name" TEXT NOT NULL
);

CREATE TABLE "customer" (
  "id"                 SERIAL CONSTRAINT "pk_customer" PRIMARY KEY,
  "name"               TEXT        NOT NULL,
  "phone"              VARCHAR(11) NOT NULL,
  "email"              TEXT        NOT NULL,
  "age"                INTEGER     NOT NULL,
  "driving_experience" INTEGER     NOT NULL
);

CREATE TABLE "location" (
  "id"      SERIAL CONSTRAINT "pk_location" PRIMARY KEY,
  "name"    TEXT        NOT NULL,
  "address" TEXT        NOT NULL,
  "phone"   VARCHAR(11) NOT NULL
);

CREATE TABLE "car" (
  "id"              SERIAL CONSTRAINT "pk_car" PRIMARY KEY,
  "name"            TEXT    NOT NULL,
  "production_year" INTEGER,
  "doors"           INTEGER,
  "places"          INTEGER,
  "motor_power"     INTEGER,
  "price"           INTEGER NOT NULL,
  "location"        INTEGER NOT NULL,
  "fuel"            INTEGER,
  "category"        INTEGER,
  "actuator"        INTEGER,
  "gearbox"         INTEGER
);

ALTER TABLE "car"
  ADD CONSTRAINT "fk_car__actuator" FOREIGN KEY ("actuator") REFERENCES "car_actuator" ("id");

ALTER TABLE "car"
  ADD CONSTRAINT "fk_car__category" FOREIGN KEY ("category") REFERENCES "car_category" ("id");

ALTER TABLE "car"
  ADD CONSTRAINT "fk_car__fuel" FOREIGN KEY ("fuel") REFERENCES "car_fuel" ("id");

ALTER TABLE "car"
  ADD CONSTRAINT "fk_car__gearbox" FOREIGN KEY ("gearbox") REFERENCES "car_gearbox" ("id");

ALTER TABLE "car"
  ADD CONSTRAINT "fk_car__location" FOREIGN KEY ("location") REFERENCES "location" ("id");

CREATE TABLE "order_status" (
  "id"   SERIAL CONSTRAINT "pk_order_status" PRIMARY KEY,
  "name" TEXT NOT NULL
);

CREATE TABLE "orders" (
  "id"           SERIAL CONSTRAINT "pk_orders" PRIMARY KEY,
  "date_start"   TIMESTAMP NOT NULL,
  "date_end"     TIMESTAMP NOT NULL,
  "customer"     INTEGER   NOT NULL,
  "status"       INTEGER   NOT NULL,
  "car_id"       INTEGER   NOT NULL,
  "car_location" INTEGER   NOT NULL
);

ALTER TABLE "orders"
  ADD CONSTRAINT "fk_orders__car_id__car_location" FOREIGN KEY ("car_id", "car_location") REFERENCES "car" ("id", "location");

ALTER TABLE "orders"
  ADD CONSTRAINT "fk_orders__customer" FOREIGN KEY ("customer") REFERENCES "customer" ("id");

ALTER TABLE "orders"
  ADD CONSTRAINT "fk_orders__status" FOREIGN KEY ("status") REFERENCES "order_status" ("id");

CREATE TABLE "payment" (
  "id"          SERIAL CONSTRAINT "pk_payment" PRIMARY KEY,
  "status"      BOOLEAN   NOT NULL,
  "price"       INTEGER   NOT NULL,
  "date_create" TIMESTAMP NOT NULL,
  "order"       INTEGER   NOT NULL
);


ALTER TABLE "payment"
  ADD CONSTRAINT "fk_payment__order" FOREIGN KEY ("order") REFERENCES "orders" ("id");

CREATE TABLE "service" (
  "id"          SERIAL CONSTRAINT "pk_service" PRIMARY KEY,
  "name"        TEXT    NOT NULL,
  "description" TEXT    NOT NULL,
  "price"       INTEGER NOT NULL
);

CREATE TABLE "order_service" (
  "id"      SERIAL CONSTRAINT "pk_order_service" PRIMARY KEY,
  "service" INTEGER NOT NULL,
  "order"   INTEGER NOT NULL
);

ALTER TABLE "order_service"
  ADD CONSTRAINT "fk_order_service__order" FOREIGN KEY ("order") REFERENCES "orders" ("id");

ALTER TABLE "order_service"
  ADD CONSTRAINT "fk_order_service__service" FOREIGN KEY ("service") REFERENCES "service" ("id")