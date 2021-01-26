-- ** insert data in tables **

-- ## table RegisteredUser ##
INSERT INTO registeredUser(email, password, role) VALUES ('sadmin1@gmail.com', 'qwerty', 'system administrator');

INSERT INTO registeredUser(email, password, role) VALUES ('dministratormen0652@gmail.com', 'qwerty', 'administrator');

INSERT INTO registeredUser(email, password, role) VALUES ('courier1@gmail.com', 'qwerty', 'courier');
INSERT INTO registeredUser(email, password, role) VALUES ('courier2@gmail.com', 'qwerty', 'courier');
INSERT INTO registeredUser(email, password, role) VALUES ('courier3@gmail.com', 'qwerty', 'courier');
INSERT INTO registeredUser(email, password, role) VALUES ('courier4@gmail.com', 'qwerty', 'courier');
INSERT INTO registeredUser(email, password, role) VALUES ('courier5@gmail.com', 'qwerty', 'courier');
INSERT INTO registeredUser(email, password, role) VALUES ('couriermen0652@gmail.com', 'qwerty', 'courier');

INSERT INTO registeredUser(email, password, role) VALUES ('client1@gmail.com', 'qwerty', 'client');
INSERT INTO registeredUser(email, password, role) VALUES ('client2@gmail.com', 'qwerty', 'client');
INSERT INTO registeredUser(email, password, role) VALUES ('client3@gmail.com', 'qwerty', 'client');


-- ## table Address ##
INSERT INTO address(address, latitude, longitude, altitude) VALUES ('casa da musica', 41.158056, 8.630556, 83);
INSERT INTO address(address, latitude, longitude, altitude) VALUES ('conservatorio', 41.155556, 8.623056, 79);
INSERT INTO address(address, latitude, longitude, altitude) VALUES ('isep', 41.178333, 8.606389, 103);
INSERT INTO address(address, latitude, longitude, altitude) VALUES ('feup', 41.1775, 8.598056, 111);
INSERT INTO address(address, latitude, longitude, altitude) VALUES ('trindade', 41.151667, 8.609444, 86);
INSERT INTO address(address, latitude, longitude, altitude) VALUES ('torre dos clerigos', 41.145833, 8.613889, 74);
INSERT INTO address(address, latitude, longitude, altitude) VALUES ('se do porto', 41.143056, 8.611111, 72);
INSERT INTO address(address, latitude, longitude, altitude) VALUES ('el corte ingles', 41.125556, 8.605278, 99);
INSERT INTO address(address, latitude, longitude, altitude) VALUES ('parque de serralves', 41.159722, 8.659722, 60);
INSERT INTO address(address, latitude, longitude, altitude) VALUES ('pavilhao rosa mota', 41.148333, 8.625278, 72);
INSERT INTO address(address, latitude, longitude, altitude) VALUES ('estadio do bessa', 41.162222, 8.643333, 66);
INSERT INTO address(address, latitude, longitude, altitude) VALUES('gaia shopping', 41.11, 8.623611, 89);
INSERT INTO address(address, latitude, longitude, altitude) VALUES('ponte da arrabida', 41.147222, 8.64, 66);

-- ## table Path ##
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('casa da musica', 'conservatorio', 0.011, 48, 5);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('conservatorio', 'casa da musica', 0.011, 228, 5);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('casa da musica', 'estadio do bessa', 0.01, 80, 2);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('estadio do bessa', 'parque de serralves', 0.012, 97, 7);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('parque de serralves', 'casa da musica', 0.01, 50, 6);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('casa da musica', 'pavilhao rosa mota', 0.99, 30, 5);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('pavilhao rosa mota', 'casa da musica', 0.99, 210, 5);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('conservatorio', 'trindade', 0.011, 140, 6);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('trindade', 'conservatorio', 0.011, 320, 6);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('conservatorio', 'pavilhao rosa mota', 0.01, 140, 6);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('pavilhao rosa mota', 'conservatorio', 0.01, 320, 6);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('pavilhao rosa mota', 'torre dos clerigos', 0.02, 90, 7);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('torre dos clerigos', 'pavilhao rosa mota', 0.02, 270, 7);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('torre dos clerigos', 'se do porto', 0.013, 79, 7);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('se do porto', 'el corte ingles', 0.014, 110, 8);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('trindade', 'isep', 0.015, 190, 6);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('isep', 'feup', 0.018, 180, 7);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('feup', 'trindade', 0.012, 290, 8);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('el corte ingles', 'gaia shopping', 0.021, 210, 6);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('gaia shopping', 'el corte ingles', 0.021, 30, 6);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('gaia shopping', 'ponte da arrabida', 0.022, 190, 10);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('ponte da arrabida', 'gaia shopping', 0.022, 10, 10);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('casa da musica', 'ponte da arrabida', 0.024, 80, 7);
INSERT INTO path(address1, address2, kinetic_coefficient, wind_angle, wind_speed) VALUES ('ponte da arrabida', 'casa da musica', 0.024, 260, 7);

-- ## table Pharmacy ##
INSERT INTO pharmacy(name, address) VALUES ('farmacia da boavista', 'casa da musica');
INSERT INTO pharmacy(name, address) VALUES ('farmacia do gaia shopping', 'gaia shopping');

-- ## table Administrator ##
INSERT INTO administrator(email, id_pharmacy, name, nif, social_security) VALUES('dministratormen0652@gmail.com', 1, 'admin1', 111111111, 11111111111);

-- ## table Courier ##
INSERT INTO courier(email, name, nif, social_security, id_pharmacy, weight) VALUES('courier1@gmail.com', 'courier1', 222222201, 22222222201, 1, 84.3);
INSERT INTO courier(email, name, nif, social_security, id_pharmacy, weight) VALUES('courier2@gmail.com', 'courier2', 222222202, 22222222202, 1, 55.0);
INSERT INTO courier(email, name, nif, social_security, id_pharmacy, weight) VALUES('courier3@gmail.com', 'courier3', 222222203, 22222222203, 1, 73.2);
INSERT INTO courier(email, name, nif, social_security, id_pharmacy, weight) VALUES('courier4@gmail.com', 'courier4', 222222204, 22222222204, 1, 60.8);
INSERT INTO courier(email, name, nif, social_security, id_pharmacy, weight) VALUES('courier5@gmail.com', 'courier5', 222222205, 22222222205, 1, 50.5);
INSERT INTO courier(email, name, nif, social_security, id_pharmacy, weight) VALUES('couriermen0652@gmail.com', 'courierParkTest', 222222210, 22222222210, 1, 50.5);

-- ## table CreditCard ##
INSERT INTO creditCard(credit_card, validity_date, ccv) VALUES(1111111111111101, TO_DATE('2025-06-16','yyyy-mm-dd'), 111);
INSERT INTO creditCard(credit_card, validity_date, ccv) VALUES(1111111111111102, TO_DATE('2025-06-16','yyyy-mm-dd'), 111);

-- ## table Client ##
INSERT INTO client(email, name, nif, credit_card, address) VALUES('client1@gmail.com', 'client1', 333333301, 1111111111111101, 'feup');
INSERT INTO client(email, name, nif, credit_card, address) VALUES('client2@gmail.com', 'client2', 333333302, 1111111111111101, 'parque de serralves');
INSERT INTO client(email, name, nif, credit_card, address) VALUES('client3@gmail.com', 'client3', 333333303, 1111111111111102, 'se do porto');

-- ## table Park ##
INSERT INTO park(id_pharmacy, limit, num_charging_stations, category, address, max_charging_potency) VALUES (1, 10, 5, 'scooter','casa da musica', 5000);
INSERT INTO park(id_pharmacy, limit, num_charging_stations, category, address, max_charging_potency) VALUES (1, 10, 5, 'drone','casa da musica', 5000);
INSERT INTO park(id_pharmacy, limit, num_charging_stations, category, address, max_charging_potency) VALUES (2, 10, 5, 'scooter','gaia shopping', 5000);
INSERT INTO park(id_pharmacy, limit, num_charging_stations, category, address, max_charging_potency) VALUES (2, 10, 5, 'drone','gaia shopping', 5000);


-- ## table ProductCategory ##
INSERT INTO productCategory(name) VALUES ('Medicamentos');
INSERT INTO productCategory(name) VALUES ('Higiene oral');
INSERT INTO productCategory(name) VALUES ('Sexualidade');
INSERT INTO productCategory(name) VALUES ('Vacinas');
INSERT INTO productCategory(name) VALUES ('Vitaminas');

-- ## table Product ##
INSERT INTO product(name, price, weight, id_category) VALUES ('Ben-u-ron', 2.40, 0.1, 1);
INSERT INTO product(name, price, weight, id_category) VALUES ('Ibuprofeno', 4.70, 0.2, 1);
INSERT INTO product(name, price, weight, id_category) VALUES ('Imodium Rapid', 7.95, 0.25, 1);

INSERT INTO product(name, price, weight, id_category) VALUES ('Escova de Dentes Colgate', 3.99, 0.08, 2);
INSERT INTO product(name, price, weight, id_category) VALUES ('Escova de Dentes Deluxe Edition', 15.99, 0.8, 2);
INSERT INTO product(name, price, weight, id_category) VALUES ('Fio Dentario Colgate', 3.49, 0.05, 2);
INSERT INTO product(name, price, weight, id_category) VALUES ('Pasta Dentes Gengivas Bonitas Oral-B', 3.36, 0.35, 2);

INSERT INTO product(name, price, weight, id_category) VALUES ('Gel Lubrificante', 25.30, 0.45, 3);
INSERT INTO product(name, price, weight, id_category) VALUES ('Preservativos Non-Stop', 10.99, 0.25, 3);

INSERT INTO product(name, price, weight, id_category) VALUES ('Vacina Mata Tudo', 7.99, 0.03, 4);

INSERT INTO product(name, price, weight, id_category) VALUES ('Saquetas Anti Stress', 9.50, 0.20, 5);
INSERT INTO product(name, price, weight, id_category) VALUES ('Vitamina C Framboesa Comprimidos', 8.40, 0.15, 5);
INSERT INTO product(name, price, weight, id_category) VALUES ('Vitamina D Menta Comprimidos', 21.65, 0.15, 5);

-- ## table Stock ##
INSERT INTO stock(id_pharmacy, id_product, quantity) VALUES (1, 1, 10);
INSERT INTO stock(id_pharmacy, id_product, quantity) VALUES (1, 2, 10);
INSERT INTO stock(id_pharmacy, id_product, quantity) VALUES (1, 4, 10);
INSERT INTO stock(id_pharmacy, id_product, quantity) VALUES (1, 5, 10);
INSERT INTO stock(id_pharmacy, id_product, quantity) VALUES (1, 9, 10);
INSERT INTO stock(id_pharmacy, id_product, quantity) VALUES (1, 11, 10);
INSERT INTO stock(id_pharmacy, id_product, quantity) VALUES (1, 12, 10);
INSERT INTO stock(id_pharmacy, id_product, quantity) VALUES (2, 1, 5);
INSERT INTO stock(id_pharmacy, id_product, quantity) VALUES (2, 2, 5);

-- ## table VehicleStatus ##
INSERT INTO vehicleStatus(id_vehicle_status, name) VALUES (1, 'available');
INSERT INTO vehicleStatus(id_vehicle_status, name) VALUES (2, 'maintenance');
INSERT INTO vehicleStatus(id_vehicle_status, name) VALUES (3, 'occupied');
INSERT INTO vehicleStatus(id_vehicle_status, name) VALUES (4, 'charging');

-- ## table DeliveryStatus ##
INSERT INTO deliveryStatus(id_delivery_status, name) VALUES (1, 'processing');
INSERT INTO deliveryStatus(id_delivery_status, name) VALUES (2, 'pending');
INSERT INTO deliveryStatus(id_delivery_status, name) VALUES (3, 'in delivery');
INSERT INTO deliveryStatus(id_delivery_status, name) VALUES (4, 'delivered');
INSERT INTO deliveryStatus(id_delivery_status, name) VALUES (5, 'cancelled');

-- ## table Vehicle ##
INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed, qrCode) VALUES(1, 1, 16, 1.1, 1.5, 1, 500, 500, 8.9, null);
INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed, qrCode) VALUES(2, 1, 13, 1.11, 1.5, 1, 500, 500, 8.7, null);
INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed, qrCode) VALUES(3, 1, 14, 1.09, 1.4, 1, 500, 500, 8.3, null);
INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed, qrCode) VALUES(4, 1, 17, 1.05, 1.45, 1, 500, 500, 9.1, null);
INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed, qrCode) VALUES(5, 1, 16.5, 1.07, 1.3, 1, 500, 500, 8, null);
INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed, qrCode) VALUES(6, 1, 4, 0.6, 0.4, 1, 500, 500, 22.9, null);
INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed, qrCode) VALUES(7, 1, 5, 0.7, 0.5, 1, 500, 500, 22.2, null);
INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed, qrCode) VALUES(8, 1, 4.5, 0.65, 0.47, 1, 500, 500, 21.9, null);
INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed, qrCode) VALUES(9, 1, 3.8, 0.55, 0.51, 1, 500, 500, 23, null);
INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed, qrCode) VALUES(10, 1, 4.1, 0.58, 0.46, 1, 500, 500, 21, null);

INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed, qrCode) VALUES(101, 1, 15, 1.1, 1.5, 1, 500, 500, 8.9, null);
INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed, qrCode) VALUES(102, 1, 13, 1.11, 1.4, 1, 500, 500, 8.9, null);
INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed, qrCode) VALUES(103, 1, 14, 1.07, 1.45, 1, 500, 500, 8.9, null);

INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed, qrCode) VALUES(111, 1, 4, 0.6, 0.47, 1, 500, 500, 20, null);
INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed, qrCode) VALUES(112, 1, 5, 0.55, 0.5, 1, 500, 500, 20, null);
INSERT INTO vehicle(id_vehicle, id_pharmacy, weight, aerodynamic_coefficient, frontal_area, motor, current_battery, max_battery, average_speed, qrCode) VALUES(113, 1, 4.5, 0.5, 0.51, 1, 500, 500, 20, null);

-- ## table Scooter ##
INSERT INTO scooter(id_scooter, id_vehicle_status) VALUES(1, 1);
INSERT INTO scooter(id_scooter, id_vehicle_status) VALUES(2, 1);
INSERT INTO scooter(id_scooter, id_vehicle_status) VALUES(3, 1);
INSERT INTO scooter(id_scooter, id_vehicle_status) VALUES(4, 1);
INSERT INTO scooter(id_scooter, id_vehicle_status) VALUES(5, 1);

INSERT INTO scooter(id_scooter, id_vehicle_status) VALUES(101, 1);
INSERT INTO scooter(id_scooter, id_vehicle_status) VALUES(102, 1);
INSERT INTO scooter(id_scooter, id_vehicle_status) VALUES(103, 1);

-- ## table Drone ##
INSERT INTO drone(id_drone, width, average_vertical_speed, id_vehicle_status) VALUES(6, 0.5, 9, 1);
INSERT INTO drone(id_drone, width, average_vertical_speed, id_vehicle_status) VALUES(7, 0.5, 8, 1);
INSERT INTO drone(id_drone, width, average_vertical_speed, id_vehicle_status) VALUES(8, 0.4, 8.5, 1);
INSERT INTO drone(id_drone, width, average_vertical_speed, id_vehicle_status) VALUES(9, 0.6, 8.7, 1);
INSERT INTO drone(id_drone, width, average_vertical_speed, id_vehicle_status) VALUES(10, 0.5, 8.3, 1);

INSERT INTO drone(id_drone, width, average_vertical_speed, id_vehicle_status) VALUES(111, 0.5, 9, 1);
INSERT INTO drone(id_drone, width, average_vertical_speed, id_vehicle_status) VALUES(112, 0.5, 9, 1);
INSERT INTO drone(id_drone, width, average_vertical_speed, id_vehicle_status) VALUES(113, 0.5, 9, 1);

-- ## table ParkingSpace ##
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (1, 1, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (2, 1, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (3, 1, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (4, 1, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (5, 1, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (6, 1, null, 1);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (7, 1, null, 1);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (8, 1, null, 1);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (9, 1, null, 1);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (10, 1, null, 1);

INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (1, 2, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (2, 2, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (3, 2, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (4, 2, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (5, 2, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (6, 2, null, 1);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (7, 2, null, 1);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (8, 2, null, 1);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (9, 2, null, 1);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (10, 2, null, 1);

INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (1, 3, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (2, 3, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (3, 3, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (4, 3, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (5, 3, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (6, 3, null, 1);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (7, 3, null, 1);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (8, 3, null, 1);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (9, 3, null, 1);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (10, 3, null, 1);

INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (1, 4, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (2, 4, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (3, 4, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (4, 4, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (5, 4, null, 0);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (6, 4, null, 1);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (7, 4, null, 1);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (8, 4, null, 1);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (9, 4, null, 1);
INSERT INTO parkingSpace(id_parking_space, id_park, id_vehicle, is_charging_station) VALUES (10, 4, null, 1);

-- ## table PurchaseOrder ##
INSERT INTO purchaseOrder(id_order, id_pharmacy, email_client, emission_date) VALUES (101, 1, 'client3@gmail.com', TO_DATE('2025-06-16','yyyy-mm-dd'));
INSERT INTO purchaseOrder(id_order, id_pharmacy, email_client, emission_date) VALUES (102, 1, 'client3@gmail.com', TO_DATE('2025-06-16','yyyy-mm-dd'));
INSERT INTO purchaseOrder(id_order, id_pharmacy, email_client, emission_date) VALUES (103, 1, 'client3@gmail.com', TO_DATE('2025-06-16','yyyy-mm-dd'));

INSERT INTO purchaseOrder(id_order, id_pharmacy, email_client, emission_date) VALUES (111, 1, 'client3@gmail.com', TO_DATE('2025-06-16','yyyy-mm-dd'));
INSERT INTO purchaseOrder(id_order, id_pharmacy, email_client, emission_date) VALUES (112, 1, 'client3@gmail.com', TO_DATE('2025-06-16','yyyy-mm-dd'));
INSERT INTO purchaseOrder(id_order, id_pharmacy, email_client, emission_date) VALUES (113, 1, 'client3@gmail.com', TO_DATE('2025-06-16','yyyy-mm-dd'));

-- ## table Delivery ##
INSERT INTO delivery(id_order, id_vehicle, email_courier, id_delivery_status) VALUES (101, 101, 'couriermen0652@gmail.com', 1);
INSERT INTO delivery(id_order, id_vehicle, email_courier, id_delivery_status) VALUES (102, 102, 'couriermen0652@gmail.com', 1);
INSERT INTO delivery(id_order, id_vehicle, email_courier, id_delivery_status) VALUES (103, 103, 'couriermen0652@gmail.com', 1);

INSERT INTO delivery(id_order, id_vehicle, id_delivery_status) VALUES (111, 111, 1);
INSERT INTO delivery(id_order, id_vehicle, id_delivery_status) VALUES (112, 112, 1);
INSERT INTO delivery(id_order, id_vehicle, id_delivery_status) VALUES (113, 113, 1);