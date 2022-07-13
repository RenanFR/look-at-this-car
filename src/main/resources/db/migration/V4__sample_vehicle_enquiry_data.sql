INSERT INTO vehicle_tbl (registration_year, car_make, model, location, colour, licence_plate)
VALUES (2013, "Toyota", "Corolla XRS 2.0 Flex 16V Aut.", "Konohagakure", "Marrom", "HTY-7065");

INSERT INTO vehicle_tbl (registration_year, car_make, model, location, colour, licence_plate)
VALUES (2014, "Nissan", "Sentra SV 2.0 Flex 16V Aut.", "Sunagakure", "Branco", "KWM-0809");

INSERT INTO vehicle_tbl (registration_year, car_make, model, location, colour, licence_plate)
VALUES (1992, "Hyundai", "Elantra GLS 1.6", "Kirigakure", "Verde", "NAE-7556");

INSERT INTO vehicle_enquiry_tbl (enquiry_date, license_plate_raw, vehicle_id)
VALUES (now(), "NAE-7556", (SELECT id FROM vehicle_tbl WHERE model = "Elantra GLS 1.6"));