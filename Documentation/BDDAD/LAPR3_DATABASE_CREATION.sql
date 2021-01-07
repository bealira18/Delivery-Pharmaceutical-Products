-- ** delete existing tables **
-- CASCADE CONSTRAINTS to delete integrity restrictions from primary and unique keys
-- PURGE deletes the table from the database and recycle bin

DROP TABLE registeredUser       CASCADE CONSTRAINTS PURGE;
DROP TABLE address		        CASCADE CONSTRAINTS PURGE;
DROP TABLE pharmacy		        CASCADE CONSTRAINTS PURGE;
DROP TABLE administrator		CASCADE CONSTRAINTS PURGE;
DROP TABLE courier		        CASCADE CONSTRAINTS PURGE;
DROP TABLE creditCard		    CASCADE CONSTRAINTS PURGE;
DROP TABLE client		        CASCADE CONSTRAINTS PURGE;
DROP TABLE scooterPark		    CASCADE CONSTRAINTS PURGE;
DROP TABLE parkingSpace		    CASCADE CONSTRAINTS PURGE;
DROP TABLE product		        CASCADE CONSTRAINTS PURGE;
DROP TABLE stock		        CASCADE CONSTRAINTS PURGE;
DROP TABLE scooterStatus		CASCADE CONSTRAINTS PURGE;
DROP TABLE deliveryStatus		CASCADE CONSTRAINTS PURGE;
DROP TABLE scooter		        CASCADE CONSTRAINTS PURGE;
DROP TABLE purchaseOrder		CASCADE CONSTRAINTS PURGE;
DROP TABLE productLine		    CASCADE CONSTRAINTS PURGE;
DROP TABLE delivery		        CASCADE CONSTRAINTS PURGE;
DROP TABLE invoice		        CASCADE CONSTRAINTS PURGE;



-- Table Creation

CREATE TABLE registeredUser (
    email       VARCHAR2(255)       CONSTRAINT pkRegisteredUserEmail          PRIMARY KEY
                                    CONSTRAINT ckRegisteredUserEmail          CHECK(REGEXP_LIKE(email, '^\S+@\S+\.\S+$')),
    password    VARCHAR2(255)       CONSTRAINT nnRegisteredUserPassword       NOT NULL,
    role        VARCHAR2(255)       CONSTRAINT nnRegisteredUserRole           NOT NULL,
                                    CONSTRAINT ckRegisteredUserRole           CHECK(role IN ('administrator','courier','client'))
);

CREATE TABLE address (
    address     VARCHAR2(255)       CONSTRAINT pkAddressAddress     PRIMARY KEY,
    longitude   VARCHAR2(255)       CONSTRAINT nnAddressLongitude   NOT NULL,
    latitude    VARCHAR2(255)       CONSTRAINT nnAddressLatitude    NOT NULL,
    altitude    VARCHAR2(255)       CONSTRAINT nnAddressAltitude    NOT NULL
);

CREATE TABLE pharmacy (
    id_pharmacy     INTEGER GENERATED AS IDENTITY   CONSTRAINT pkPharmacyIdPharmacy         PRIMARY KEY,
    name            VARCHAR2(255)                   CONSTRAINT nnPharmacyName               NOT NULL,
    address         VARCHAR2(255)
);

CREATE TABLE administrator (
    email               VARCHAR2(255)       CONSTRAINT pkAdministratorEmail             PRIMARY KEY,
    id_pharmacy         INTEGER             CONSTRAINT nnAdministratorIdPharmacy        NOT NULL,
    name                VARCHAR2(255)       CONSTRAINT nnAdministratorName              NOT NULL,
    nif                 INTEGER             CONSTRAINT nnAdministratorNif               NOT NULL
                                            CONSTRAINT ukAdministratorNif               UNIQUE
                                            CONSTRAINT ckAdministratorNif               CHECK(REGEXP_LIKE(nif, '^\d{9}$')),
    social_security     INTEGER             CONSTRAINT nnAdministratorSocialSecurity    NOT NULL
                                            CONSTRAINT ukAdministratorSocialSecurity    UNIQUE
                                            CONSTRAINT ckAdministratorSocialSecurity    CHECK(REGEXP_LIKE(social_security, '^\d{11}$'))
);

CREATE TABLE courier (
    email               VARCHAR2(255)       CONSTRAINT pkCourierEmail           PRIMARY KEY,
    name                VARCHAR2(255)       CONSTRAINT nnCourierName            NOT NULL,
    nif                 INTEGER             CONSTRAINT nnCourierNif             NOT NULL
                                            CONSTRAINT ukCourierNif             UNIQUE
                                            CONSTRAINT ckCourierNif             CHECK(REGEXP_LIKE(nif, '^\d{9}$')),
    social_security     INTEGER             CONSTRAINT nnCourierSocialSecurity  NOT NULL
                                            CONSTRAINT ukCourierSocialSecurity  UNIQUE
                                            CONSTRAINT ckCourierSocialSecurity  CHECK(REGEXP_LIKE(social_security, '^\d{11}$')),
    id_pharmacy         INTEGER
);
                    
CREATE TABLE creditCard (
    credit_card         INTEGER             CONSTRAINT pkCreditCardCreditCard           PRIMARY KEY
                                            CONSTRAINT ckCreditCard                     CHECK(REGEXP_LIKE(credit_card, '^\d{16}$')),
    validity_date       DATE                CONSTRAINT nnCreditCardValidityDate         NOT NULL,
    ccv                 INTEGER             CONSTRAINT pkCreditCardCCV                  NOT NULL
                                            CONSTRAINT ckCreditCardCCV                  CHECK(REGEXP_LIKE(ccv, '^\d{3}$'))
);

CREATE TABLE client (
    email           VARCHAR2(255)            CONSTRAINT pkClientEmail            PRIMARY KEY,
    name            VARCHAR2(255)            CONSTRAINT nnClientName             NOT NULL,
    nif             INTEGER                  CONSTRAINT nnClientNif              NOT NULL
                                             CONSTRAINT ukClientNif              UNIQUE
                                             CONSTRAINT ckClientNif              CHECK(REGEXP_LIKE(nif, '^\d{9}$')),
    credit_card     INTEGER,
    credits         INTEGER DEFAULT 0,
    address         VARCHAR2(255)
);

CREATE TABLE scooterPark (
    id_park                 INTEGER GENERATED AS IDENTITY   CONSTRAINT pkScooterParkIdPark              PRIMARY KEY,
    id_pharmacy             INTEGER,
    limit                   INTEGER                         CONSTRAINT nnScooterParkLimit               NOT NULL,
    num_charging_stations   INTEGER                         CONSTRAINT nnNumChargingStations            NOT NULL,
    address                 VARCHAR2(255)
);

CREATE TABLE parkingSpace (
    id_parking_space        INTEGER,
    id_park                 INTEGER,
    id_scooter              INTEGER,
    is_charging_station     NUMBER(1) DEFAULT 0     CONSTRAINT nnParkingSpaceIsChargingStation  NOT NULL
                                                    CONSTRAINT ckParkingSpaceIsChargingStation  CHECK(is_charging_station IN(0,1)), 
                                                    
    CONSTRAINT pkParkingSpaceIdParkingSpaceIdPark   PRIMARY KEY(id_parking_space, id_park)
);




-- Foreign Keys

ALTER TABLE pharmacy            ADD CONSTRAINT fkPharmacyAddress                FOREIGN KEY(address)            REFERENCES address (address);

ALTER TABLE administrator       ADD CONSTRAINT fkAdministratorEmail             FOREIGN KEY(email)              REFERENCES registeredUser (email);
ALTER TABLE administrator       ADD CONSTRAINT fkAdministratorIdPharmacy        FOREIGN KEY(id_pharmacy)        REFERENCES pharmacy (id_pharmacy);

ALTER TABLE courier             ADD CONSTRAINT fkCourierEmail                   FOREIGN KEY(email)              REFERENCES registeredUser (email);
ALTER TABLE courier             ADD CONSTRAINT fkCourierIdPharmacy              FOREIGN KEY(id_pharmacy)        REFERENCES pharmacy (id_pharmacy);

ALTER TABLE client              ADD CONSTRAINT fkClientEmail                    FOREIGN KEY(email)              REFERENCES registeredUser (email);
ALTER TABLE client              ADD CONSTRAINT fkClientCreditCard               FOREIGN KEY(credit_card)        REFERENCES creditCard (credit_card);
ALTER TABLE client              ADD CONSTRAINT fkClientAddress                  FOREIGN KEY(address)            REFERENCES address (address);

ALTER TABLE scooterPark         ADD CONSTRAINT fkScooterParkIdPharmacy          FOREIGN KEY(id_pharmacy)        REFERENCES pharmacy (id_pharmacy);
ALTER TABLE scooterPark         ADD CONSTRAINT fkScooterParkAddress             FOREIGN KEY(address)            REFERENCES address (address);

ALTER TABLE parkingSpace        ADD CONSTRAINT fkParkingSpaceIdPark             FOREIGN KEY(id_park)            REFERENCES scooterPark (id_park);
ALTER TABLE parkingSpace        ADD CONSTRAINT fkParkingSpaceIdScooter          FOREIGN KEY(id_scooter)         REFERENCES scooter (id_scooter);

