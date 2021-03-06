CREATE TABLE car_actuator (
  ID NUMBER(10) CONSTRAINT PK_CAR_ACTUATOR PRIMARY KEY,
  name VARCHAR2(1000 CHAR) NOT NULL
);

CREATE SEQUENCE car_actuator_SEQ NOCACHE;

CREATE TRIGGER car_actuator_BI
  BEFORE INSERT ON car_actuator
  FOR EACH ROW
  BEGIN
    IF :new.ID IS NULL THEN
      SELECT car_actuator_SEQ.nextval INTO :new.ID FROM DUAL;
    END IF;
  END;;

CREATE TABLE car_category (
  ID NUMBER(10) CONSTRAINT PK_CAR_CATEGORY PRIMARY KEY,
  name VARCHAR2(1000 CHAR) NOT NULL,
  driver_age_min NUMBER(10) NOT NULL,
  driver_experience_min NUMBER(10) NOT NULL
);

CREATE SEQUENCE car_category_SEQ NOCACHE;

CREATE TRIGGER car_category_BI
  BEFORE INSERT ON car_category
  FOR EACH ROW
  BEGIN
    IF :new.ID IS NULL THEN
      SELECT car_category_SEQ.nextval INTO :new.ID FROM DUAL;
    END IF;
  END;;

CREATE TABLE car_fuel (
  ID NUMBER(10) CONSTRAINT PK_CAR_FUEL PRIMARY KEY,
  name VARCHAR2(1000 CHAR) NOT NULL
);

CREATE SEQUENCE car_fuel_SEQ NOCACHE;

CREATE TRIGGER car_fuel_BI
  BEFORE INSERT ON car_fuel
  FOR EACH ROW
  BEGIN
    IF :new.ID IS NULL THEN
      SELECT car_fuel_SEQ.nextval INTO :new.ID FROM DUAL;
    END IF;
  END;;

CREATE TABLE car_gearbox (
  ID NUMBER(10) CONSTRAINT PK_CAR_GEARBOX PRIMARY KEY,
  name VARCHAR2(1000 CHAR) NOT NULL
);

CREATE SEQUENCE car_gearbox_SEQ NOCACHE;

CREATE TRIGGER car_gearbox_BI
  BEFORE INSERT ON car_gearbox
  FOR EACH ROW
  BEGIN
    IF :new.ID IS NULL THEN
      SELECT car_gearbox_SEQ.nextval INTO :new.ID FROM DUAL;
    END IF;
  END;;

CREATE TABLE customer (
  ID NUMBER(10) CONSTRAINT PK_CUSTOMER PRIMARY KEY,
  name VARCHAR2(1000 CHAR) NOT NULL,
  phone VARCHAR2(11 CHAR) NOT NULL,
  email VARCHAR2(1000 CHAR),
  age NUMBER(10) NOT NULL,
  driving_experience NUMBER(10) NOT NULL
);

CREATE SEQUENCE customer_SEQ NOCACHE;

CREATE TRIGGER customer_BI
  BEFORE INSERT ON customer
  FOR EACH ROW
  BEGIN
    IF :new.ID IS NULL THEN
      SELECT customer_SEQ.nextval INTO :new.ID FROM DUAL;
    END IF;
  END;;

CREATE TABLE location (
  ID NUMBER(10) CONSTRAINT PK_LOCATION PRIMARY KEY,
  name VARCHAR2(1000 CHAR) NOT NULL,
  address VARCHAR2(1000 CHAR) NOT NULL,
  phone VARCHAR2(11 CHAR)
);

CREATE SEQUENCE location_SEQ NOCACHE;

CREATE TRIGGER location_BI
  BEFORE INSERT ON location
  FOR EACH ROW
  BEGIN
    IF :new.ID IS NULL THEN
      SELECT location_SEQ.nextval INTO :new.ID FROM DUAL;
    END IF;
  END;;

CREATE TABLE car (
  ID NUMBER(10) CONSTRAINT PK_CAR PRIMARY KEY,
  name VARCHAR2(1000 CHAR) NOT NULL,
  production_year NUMBER(10),
  doors NUMBER(10),
  places NUMBER(10),
  motor_power NUMBER(10),
  price NUMBER(10) NOT NULL,
  LOCATION NUMBER(10) NOT NULL,
  FUEL NUMBER(10),
  CATEGORY NUMBER(10),
  ACTUATOR NUMBER(10),
  GEARBOX NUMBER(10)
);

CREATE SEQUENCE car_SEQ NOCACHE;

CREATE TRIGGER car_BI
  BEFORE INSERT ON car
  FOR EACH ROW
  BEGIN
    IF :new.ID IS NULL THEN
      SELECT car_SEQ.nextval INTO :new.ID FROM DUAL;
    END IF;
  END;;

ALTER TABLE car ADD CONSTRAINT FK_CAR__ACTUATOR FOREIGN KEY (ACTUATOR) REFERENCES car_actuator (ID);

ALTER TABLE car ADD CONSTRAINT FK_CAR__CATEGORY FOREIGN KEY (CATEGORY) REFERENCES car_category (ID);

ALTER TABLE car ADD CONSTRAINT FK_CAR__FUEL FOREIGN KEY (FUEL) REFERENCES car_fuel (ID);

ALTER TABLE car ADD CONSTRAINT FK_CAR__GEARBOX FOREIGN KEY (GEARBOX) REFERENCES car_gearbox (ID);

ALTER TABLE car ADD CONSTRAINT FK_CAR__LOCATION FOREIGN KEY (LOCATION) REFERENCES location (ID);

CREATE TABLE order_status (
  ID NUMBER(10) CONSTRAINT PK_ORDER_STATUS PRIMARY KEY,
  NAME VARCHAR2(1000 CHAR) NOT NULL
);

CREATE SEQUENCE order_status_SEQ NOCACHE;

CREATE TRIGGER order_status_BI
  BEFORE INSERT ON order_status
  FOR EACH ROW
  BEGIN
    IF :new.ID IS NULL THEN
      SELECT order_status_SEQ.nextval INTO :new.ID FROM DUAL;
    END IF;
  END;;

CREATE TABLE orders (
  ID NUMBER(10) CONSTRAINT PK_ORDERS PRIMARY KEY,
  date_start TIMESTAMP NOT NULL,
  date_end TIMESTAMP NOT NULL,
  CUSTOMER NUMBER(10) NOT NULL,
  STATUS NUMBER(10) NOT NULL,
  CAR_ID NUMBER(10) NOT NULL
);


ALTER TABLE orders ADD CONSTRAINT FK_ORDERS__CAR_ID FOREIGN KEY (CAR_ID) REFERENCES car (ID);

ALTER TABLE orders ADD CONSTRAINT FK_ORDERS__CUSTOMER FOREIGN KEY (CUSTOMER) REFERENCES customer (ID);

ALTER TABLE orders ADD CONSTRAINT FK_ORDERS__STATUS FOREIGN KEY (STATUS) REFERENCES order_status (ID);

CREATE SEQUENCE orders_SEQ NOCACHE;

CREATE TRIGGER orders_BI
  BEFORE INSERT ON orders
  FOR EACH ROW
  BEGIN
    IF :new.ID IS NULL THEN
      SELECT orders_SEQ.nextval INTO :new.ID FROM DUAL;
    END IF;
  END;;

CREATE TABLE payment (
  ID NUMBER(10) CONSTRAINT PK_PAYMENT PRIMARY KEY,
  status NUMBER(1) NOT NULL,
  price NUMBER(10) NOT NULL,
  date_create TIMESTAMP NOT NULL,
  ORDER_ID NUMBER(10) NOT NULL
);


ALTER TABLE payment ADD CONSTRAINT FK_PAYMENT__ORDER FOREIGN KEY (ORDER_ID) REFERENCES orders (ID);

CREATE SEQUENCE payment_SEQ NOCACHE;

CREATE TRIGGER payment_BI
  BEFORE INSERT ON payment
  FOR EACH ROW
  BEGIN
    IF :new.ID IS NULL THEN
      SELECT payment_SEQ.nextval INTO :new.ID FROM DUAL;
    END IF;
  END;;

CREATE TABLE service (
  ID NUMBER(10) CONSTRAINT PK_SERVICE PRIMARY KEY,
  name VARCHAR2(1000 CHAR) NOT NULL,
  description VARCHAR2(1000 CHAR) NOT NULL,
  price NUMBER(10) NOT NULL
);

CREATE SEQUENCE service_SEQ NOCACHE;

CREATE TRIGGER service_BI
  BEFORE INSERT ON service
  FOR EACH ROW
  BEGIN
    IF :new.ID IS NULL THEN
      SELECT service_SEQ.nextval INTO :new.ID FROM DUAL;
    END IF;
  END;;

CREATE TABLE order_service (
  ID NUMBER(10) CONSTRAINT PK_order_service PRIMARY KEY,
  SERVICE NUMBER(10) NOT NULL,
  ORDER_ID NUMBER(10) NOT NULL
);

ALTER TABLE order_service ADD CONSTRAINT FK_order_service__ORDER FOREIGN KEY (ORDER_ID) REFERENCES orders (ID);

ALTER TABLE order_service ADD CONSTRAINT FK_order_service__SERVICE FOREIGN KEY (SERVICE) REFERENCES service (ID);

CREATE SEQUENCE order_service_SEQ NOCACHE;

CREATE TRIGGER order_service_BI
  BEFORE INSERT ON order_service
  FOR EACH ROW
  BEGIN
    IF :new.ID IS NULL THEN
      SELECT order_service_SEQ.nextval INTO :new.ID FROM DUAL;
    END IF;
  END;