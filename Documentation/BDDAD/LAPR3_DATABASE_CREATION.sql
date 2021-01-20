-- ** delete existing tables **
-- CASCADE CONSTRAINTS to delete integrity restrictions from primary and unique keys
-- PURGE deletes the table from the database and recycle bin

DROP TABLE registeredUser       CASCADE CONSTRAINTS PURGE;
DROP TABLE address		        CASCADE CONSTRAINTS PURGE;
DROP TABLE path		            CASCADE CONSTRAINTS PURGE;
DROP TABLE pharmacy		        CASCADE CONSTRAINTS PURGE;
DROP TABLE administrator		CASCADE CONSTRAINTS PURGE;
DROP TABLE courier		        CASCADE CONSTRAINTS PURGE;
DROP TABLE creditCard		    CASCADE CONSTRAINTS PURGE;
DROP TABLE client		        CASCADE CONSTRAINTS PURGE;
DROP TABLE park		            CASCADE CONSTRAINTS PURGE;
DROP TABLE parkingSpace		    CASCADE CONSTRAINTS PURGE;
DROP TABLE productCategory      CASCADE CONSTRAINTS PURGE;
DROP TABLE product		        CASCADE CONSTRAINTS PURGE;
DROP TABLE stock		        CASCADE CONSTRAINTS PURGE;
DROP TABLE vehicleStatus		CASCADE CONSTRAINTS PURGE;
DROP TABLE deliveryStatus		CASCADE CONSTRAINTS PURGE;
DROP TABLE vehicle		        CASCADE CONSTRAINTS PURGE;
DROP TABLE scooter		        CASCADE CONSTRAINTS PURGE;
DROP TABLE drone                CASCADE CONSTRAINTS PURGE;
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
                                    CONSTRAINT ckRegisteredUserRole           CHECK(role IN ('system administrator', 'administrator','courier','client'))
);

CREATE TABLE address (
    address     VARCHAR2(255)       CONSTRAINT pkAddressAddress     PRIMARY KEY,
    latitude    NUMERIC(19,10)      CONSTRAINT nnAddressLatitude    NOT NULL,
    longitude   NUMERIC(19,10)      CONSTRAINT nnAddressLongitude   NOT NULL,
    altitude    NUMERIC(19,10)      CONSTRAINT nnAddressAltitude    NOT NULL
);

CREATE TABLE path (
    address1                VARCHAR2(255)         CONSTRAINT nnPathAddress1             NOT NULL,
    address2                VARCHAR2(255)         CONSTRAINT nnPathAddress2             NOT NULL,
    kinetic_coefficient     NUMERIC(5,2)          CONSTRAINT nnPathKineticCoefficient   NOT NULL
                                                  CONSTRAINT ckPathKineticCoefficient   CHECK(kinetic_coefficient>=0),
    wind_angle              NUMERIC(5,2)          CONSTRAINT nnPathWindAngle            NOT NULL
                                                  CONSTRAINT ckPathWindAngle            CHECK(wind_angle BETWEEN 0 AND 360),
    wind_speed              NUMERIC(6,2)          CONSTRAINT nnPathWindSpeed            NOT NULL
                                                  CONSTRAINT ckPathWindSpeed            CHECK(wind_speed >= 0),
                                                  
    CONSTRAINT pkPathAddress1Address2   PRIMARY KEY(address1, address2)   
);

CREATE TABLE pharmacy (
    id_pharmacy     INTEGER GENERATED AS IDENTITY   CONSTRAINT pkPharmacyIdPharmacy         PRIMARY KEY,
    name            VARCHAR2(255)                   CONSTRAINT nnPharmacyName               NOT NULL,
    address         VARCHAR2(255)                   CONSTRAINT nnPharmacyAddress            NOT NULL
);

CREATE TABLE administrator (
    email               VARCHAR2(255)       CONSTRAINT pkAdministratorEmail             PRIMARY KEY,
    id_pharmacy         INTEGER             CONSTRAINT nnAdministratorIdPharmacy        NOT NULL
                                            CONSTRAINT ukAdministratorIdPharmacy        UNIQUE,
    name                VARCHAR2(255)       CONSTRAINT nnAdministratorName              NOT NULL,
    nif                 INTEGER             CONSTRAINT nnAdministratorNif               NOT NULL
                                            CONSTRAINT ukAdministratorNif               UNIQUE
                                            CONSTRAINT ckAdministratorNif               CHECK(REGEXP_LIKE(nif, '^\d{9}$')),
    social_security     NUMERIC(12,0)       CONSTRAINT nnAdministratorSocialSecurity    NOT NULL
                                            CONSTRAINT ukAdministratorSocialSecurity    UNIQUE
                                            CONSTRAINT ckAdministratorSocialSecurity    CHECK(REGEXP_LIKE(social_security, '^\d{11}$'))
);

CREATE TABLE courier (
    email               VARCHAR2(255)       CONSTRAINT pkCourierEmail           PRIMARY KEY,
    name                VARCHAR2(255)       CONSTRAINT nnCourierName            NOT NULL,
    nif                 NUMBER(10)          CONSTRAINT nnCourierNif             NOT NULL
                                            CONSTRAINT ukCourierNif             UNIQUE
                                            CONSTRAINT ckCourierNif             CHECK(REGEXP_LIKE(nif, '^\d{9}$')),
    social_security     NUMERIC(12,0)       CONSTRAINT nnCourierSocialSecurity  NOT NULL
                                            CONSTRAINT ukCourierSocialSecurity  UNIQUE
                                            CONSTRAINT ckCourierSocialSecurity  CHECK(REGEXP_LIKE(social_security, '^\d{11}$')),
    id_pharmacy         INTEGER             CONSTRAINT nnCourierIdPharmacy      NOT NULL,
    weight              NUMERIC(5,2)        CONSTRAINT nnCourierWeight          NOT NULL
                                            CONSTRAINT ckCourierWeight          CHECK(weight>0)
);
                    
CREATE TABLE creditCard (
    credit_card         NUMBER(16)          CONSTRAINT pkCreditCardCreditCard           PRIMARY KEY
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
    credit_card     NUMBER(16)               CONSTRAINT nnClientCreditCard       NOT NULL,
    address         VARCHAR2(255)            CONSTRAINT nnClientAdress           NOT NULL,
    credits         INTEGER DEFAULT 0        CONSTRAINT nnClientCredits          NOT NULL
);

CREATE TABLE park (
    id_park                 INTEGER GENERATED AS IDENTITY   CONSTRAINT pkParkIdPark               PRIMARY KEY,
    id_pharmacy             INTEGER                         CONSTRAINT nnParkIdPharmacy           NOT NULL,
    limit                   INTEGER                         CONSTRAINT nnParkLimit                NOT NULL,
    num_charging_stations   INTEGER                         CONSTRAINT nnParkNumChargingStations  NOT NULL,
    category                VARCHAR2(255)                   CONSTRAINT nnParkCategory             NOT NULL
                                                            CONSTRAINT ckParkCategory             CHECK(category IN('scooter', 'drone')),
    address                 VARCHAR2(255)                   CONSTRAINT nnParkAdress               NOT NULL,
    
    CONSTRAINT ckScooterParkLimitNumChargingStations   CHECK(limit>=num_charging_stations)
);

CREATE TABLE productCategory (
    id_category     INTEGER GENERATED AS IDENTITY      CONSTRAINT pkProductCategoryIdCategory   PRIMARY KEY,
    name            VARCHAR2(255)                      CONSTRAINT nnProductCategoryName         NOT NULL
                                                       CONSTRAINT ukProductCategoryName         UNIQUE                                                  
);

CREATE TABLE product (
    id_product      INTEGER GENERATED AS IDENTITY      CONSTRAINT pkProductIdProduct            PRIMARY KEY,
    name            VARCHAR2(255)                      CONSTRAINT nnProductName                 NOT NULL
                                                       CONSTRAINT ukProductName                 UNIQUE,
    price           NUMERIC(9,2)                       CONSTRAINT nnProductPrice                NOT NULL
                                                       CONSTRAINT ckProductPrice                CHECK(price>0),
    weight          NUMERIC(9,2)                       CONSTRAINT nnProductWeight               NOT NULL
                                                       CONSTRAINT ckProductWeight               CHECK(weight>0),
    id_category     INTEGER                            CONSTRAINT nnProductIdCategory           NOT NULL           
);

CREATE TABLE stock (
    id_pharmacy           INTEGER        CONSTRAINT nnStockIdPharmacy             NOT NULL,              
    id_product            INTEGER        CONSTRAINT nnStockIdProduct              NOT NULL,                 
    quantity              INTEGER        CONSTRAINT nnStockQuantity               NOT NULL
                                         CONSTRAINT ckStockQuantity               CHECK(quantity>=0),
                                         
    CONSTRAINT pkStockIdPharmacyProduct   PRIMARY KEY(id_pharmacy, id_product)
);

CREATE TABLE vehicleStatus (
 id_vehicle_status     INTEGER                           CONSTRAINT pkVehicleStatusIdVehicleStatus  PRIMARY KEY,            
 name                  VARCHAR2(255)                     CONSTRAINT nnVehicleStatusName             NOT NULL
                                                         CONSTRAINT ukVehicleStatusName             UNIQUE
                                                         CONSTRAINT ckVehicleStatusName             CHECK(name IN ('available','maintenance','occupied','charging'))                                                 
);

CREATE TABLE deliveryStatus (
    id_delivery_status    INTEGER                           CONSTRAINT pkDeliveryStatusId              PRIMARY KEY,            
    name                  VARCHAR2(255)                     CONSTRAINT nnDeliveryStatusName            NOT NULL
                                                            CONSTRAINT ukDeliveryStatusName            UNIQUE
                                                            CONSTRAINT ckDeliveryStatusName            CHECK(name IN ('processing','pending', 'in delivery', 'delivered'))                                                 
);

CREATE TABLE vehicle (
    id_vehicle              INTEGER                           CONSTRAINT pkVehicleId                     PRIMARY KEY,            
    id_pharmacy             INTEGER                           CONSTRAINT nnVehicleIdPharmacy             NOT NULL, 
    weight                  NUMERIC(5,2)                      CONSTRAINT nnVehicleWeight                 NOT NULL
                                                              CONSTRAINT ckVehicleWeight                 CHECK(weight>0),
    aerodynamic_coefficient NUMERIC(5,2)                      CONSTRAINT nnVehicleAerodynamicCoefficient NOT NULL
                                                              CONSTRAINT ckVehicleAerodynamicCoefficient CHECK(aerodynamic_coefficient>=0),
    frontal_area            NUMERIC(5,2)                      CONSTRAINT nnVehicleFrontalArea            NOT NULL
                                                              CONSTRAINT ckVehicleFrontalArea            CHECK(frontal_area>0), 
    motor                   NUMERIC(9,2)                      CONSTRAINT nnVehicleMotor                  NOT NULL
                                                              CONSTRAINT ckVehicleMotor                  CHECK(motor>0), 
    current_battery         NUMERIC(9,2)                      CONSTRAINT nnVehicleCurrentBattery         NOT NULL
                                                              CONSTRAINT ckVehicleCurrentBattery         CHECK(current_battery>=0),
    max_battery             NUMERIC(9,2)                      CONSTRAINT nnVehicleMaxBattery             NOT NULL 
                                                              CONSTRAINT ckVehicleMaxBattery             CHECK(max_battery>0)
);

CREATE TABLE scooter (
    id_scooter            INTEGER                           CONSTRAINT pkScooterIdScooter       PRIMARY KEY,                             
    id_vehicle_status     INTEGER                           CONSTRAINT nnScooterIdVehicleStatus NOT NULL
);

CREATE TABLE drone (
    id_drone                INTEGER                       CONSTRAINT pkDroneIdDrone           PRIMARY KEY,                             
    id_vehicle_status       INTEGER                       CONSTRAINT nnDroneIdVehicleStatus   NOT NULL
);

CREATE TABLE parkingSpace (
    id_parking_space        INTEGER,
    id_park                 INTEGER                 CONSTRAINT nnParkingSpaceIdPark             NOT NULL,
    id_vehicle              INTEGER,
    is_charging_station     NUMBER(1) DEFAULT 0     CONSTRAINT nnParkingSpaceIsChargingStation  NOT NULL
                                                    CONSTRAINT ckParkingSpaceIsChargingStation  CHECK(is_charging_station IN(0,1)), 
                                                    
    CONSTRAINT pkParkingSpaceIdParkingSpaceIdPark   PRIMARY KEY(id_parking_space, id_park)
);

CREATE TABLE purchaseOrder (
    id_order              INTEGER GENERATED AS IDENTITY     CONSTRAINT pkOrderId                   PRIMARY KEY,            
    id_pharmacy           INTEGER                           CONSTRAINT nnPurchaseOrderIdPharmacy   NOT NULL,                 
    email_client          VARCHAR2(255)                     CONSTRAINT nnPurchaseOrderEmailClient  NOT NULL,
    emission_date         DATE              
);

CREATE TABLE productLine (
    id_order              INTEGER        CONSTRAINT nnProductLineIdOrder          NOT NULL,              
    id_product            INTEGER        CONSTRAINT nnProductLineIdProduct        NOT NULL,                 
    product_quantity      INTEGER        CONSTRAINT nnProductLineProductQuantity  NOT NULL,
    price                 NUMERIC(9,2)   CONSTRAINT nnProductLinePrice            NOT NULL,  
    
    CONSTRAINT pkProductLineIdOrderProduct   PRIMARY KEY(id_order, id_product)
);

CREATE TABLE delivery (
    id_order              INTEGER              CONSTRAINT pkDeliveryIdOrder               PRIMARY KEY
                                               CONSTRAINT nnDeliveryIdOrder               NOT NULL,
    id_vehicle            INTEGER              CONSTRAINT nnDeliveryIdVehicle             NOT NULL,       
    email_courier         VARCHAR2(255)        CONSTRAINT nnDeliveryEmailCourier          NOT NULL,
    id_delivery_status    INTEGER              CONSTRAINT nnDeliveryIdDeliveryStatus      NOT NULL,
    delivery_start        DATE,           
    delivery_end          DATE,
    delivery_run          INTEGER,
    
    CONSTRAINT ckDeliveryEndDeliveryStart      CHECK(delivery_end > delivery_start)   
);

CREATE TABLE invoice (
    id_invoice      INTEGER GENERATED AS IDENTITY         CONSTRAINT pkInvoiceIDInvoice            PRIMARY KEY,
    id_order        INTEGER                               CONSTRAINT nnInvoiceIdOrder              NOT NULL,
    id_pharmacy     INTEGER                               CONSTRAINT nnInvoiceIdPharmacy           NOT NULL,
    email_client    VARCHAR2(255)                         CONSTRAINT nnInvoiceEmailClient          NOT NULL,
    total_price     NUMERIC(9,2)                          CONSTRAINT nnInvoiceTotalPrice           NOT NULL,
    delivery_fee    INTEGER,
                                                          CONSTRAINT ckInvoiceTotalPriceNotZero    CHECK(total_price>=0)
);


-- Foreign Keys

ALTER TABLE path                ADD CONSTRAINT fkPathAddress1                   FOREIGN KEY(address1)           REFERENCES address (address);
ALTER TABLE path                ADD CONSTRAINT fkPathAddress2                   FOREIGN KEY(address2)           REFERENCES address (address);

ALTER TABLE pharmacy            ADD CONSTRAINT fkPharmacyAddress                FOREIGN KEY(address)            REFERENCES address (address);

ALTER TABLE administrator       ADD CONSTRAINT fkAdministratorEmail             FOREIGN KEY(email)              REFERENCES registeredUser (email);
ALTER TABLE administrator       ADD CONSTRAINT fkAdministratorIdPharmacy        FOREIGN KEY(id_pharmacy)        REFERENCES pharmacy (id_pharmacy);

ALTER TABLE courier             ADD CONSTRAINT fkCourierEmail                   FOREIGN KEY(email)              REFERENCES registeredUser (email);
ALTER TABLE courier             ADD CONSTRAINT fkCourierIdPharmacy              FOREIGN KEY(id_pharmacy)        REFERENCES pharmacy (id_pharmacy);

ALTER TABLE client              ADD CONSTRAINT fkClientEmail                    FOREIGN KEY(email)              REFERENCES registeredUser (email);
ALTER TABLE client              ADD CONSTRAINT fkClientCreditCard               FOREIGN KEY(credit_card)        REFERENCES creditCard (credit_card);
ALTER TABLE client              ADD CONSTRAINT fkClientAddress                  FOREIGN KEY(address)            REFERENCES address (address);

ALTER TABLE park                ADD CONSTRAINT fkParkIdPharmacy                 FOREIGN KEY(id_pharmacy)        REFERENCES pharmacy (id_pharmacy);
ALTER TABLE park                ADD CONSTRAINT fkParkAddress                    FOREIGN KEY(address)            REFERENCES address (address);

ALTER TABLE product             ADD CONSTRAINT fkProductCategoryId              FOREIGN KEY(id_category)        REFERENCES productCategory (id_category);

ALTER TABLE stock               ADD CONSTRAINT fkStockPharmacyId                FOREIGN KEY(id_pharmacy)        REFERENCES pharmacy (id_pharmacy);
ALTER TABLE stock               ADD CONSTRAINT fkStockProductId                 FOREIGN KEY(id_product)         REFERENCES product (id_product);

ALTER TABLE vehicle             ADD CONSTRAINT fkVehiclePharmacyId              FOREIGN KEY(id_pharmacy)        REFERENCES pharmacy (id_pharmacy);

ALTER TABLE scooter             ADD CONSTRAINT fkScooterScooterId               FOREIGN KEY(id_scooter)         REFERENCES vehicle (id_vehicle);
ALTER TABLE scooter             ADD CONSTRAINT fkScooterStatusId                FOREIGN KEY(id_vehicle_status)  REFERENCES vehicleStatus (id_vehicle_status);

ALTER TABLE drone               ADD CONSTRAINT fkDroneDroneId                   FOREIGN KEY(id_drone)           REFERENCES vehicle (id_vehicle);
ALTER TABLE drone               ADD CONSTRAINT fkDroneStatusId                  FOREIGN KEY(id_vehicle_status)  REFERENCES vehicleStatus (id_vehicle_status);

ALTER TABLE parkingSpace        ADD CONSTRAINT fkParkingSpaceIdPark             FOREIGN KEY(id_park)            REFERENCES park (id_park);
ALTER TABLE parkingSpace        ADD CONSTRAINT fkParkingSpaceIdScooter          FOREIGN KEY(id_vehicle)         REFERENCES vehicle (id_vehicle);

ALTER TABLE purchaseOrder       ADD CONSTRAINT fkPurchaseOrderPharmacyId        FOREIGN KEY(id_pharmacy)        REFERENCES pharmacy (id_pharmacy);
ALTER TABLE purchaseOrder       ADD CONSTRAINT fkPurchaseOrderClientEmail       FOREIGN KEY(email_client)       REFERENCES client (email);

ALTER TABLE productLine         ADD CONSTRAINT fkProductLineOrderId             FOREIGN KEY(id_order)           REFERENCES purchaseOrder (id_order);
ALTER TABLE productLine         ADD CONSTRAINT fkProductLineProductId           FOREIGN KEY(id_product)         REFERENCES product (id_product);

ALTER TABLE delivery            ADD CONSTRAINT fkDeliveryOrderId                FOREIGN KEY(id_order)           REFERENCES purchaseOrder (id_order);
ALTER TABLE delivery            ADD CONSTRAINT fkDeliveryVehicleId              FOREIGN KEY(id_vehicle)         REFERENCES vehicle (id_vehicle);
ALTER TABLE delivery            ADD CONSTRAINT fkDeliveryCourierEmail           FOREIGN KEY(email_courier)      REFERENCES courier (email);
ALTER TABLE delivery            ADD CONSTRAINT fkDeliveryDeliveryStatusId       FOREIGN KEY(id_delivery_status) REFERENCES deliveryStatus (id_delivery_status);

ALTER TABLE invoice             ADD CONSTRAINT fkInvoiceOrderId                 FOREIGN KEY(id_order)           REFERENCES purchaseOrder (id_order);
ALTER TABLE invoice             ADD CONSTRAINT fkInvoicePharmacyId              FOREIGN KEY(id_pharmacy)        REFERENCES pharmacy (id_pharmacy);
ALTER TABLE invoice             ADD CONSTRAINT fkInvoiceClientEmail             FOREIGN KEY(email_client)       REFERENCES client (email);