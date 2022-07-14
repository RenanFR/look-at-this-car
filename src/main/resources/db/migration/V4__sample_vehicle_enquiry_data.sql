ALTER TABLE vehicle_tbl ALTER COLUMN image_url DROP NOT NULL;
ALTER TABLE vehicle_tbl ALTER COLUMN vin DROP NOT NULL;
ALTER TABLE vehicle_tbl ALTER COLUMN fuel DROP NOT NULL;
ALTER TABLE vehicle_tbl ALTER COLUMN power DROP NOT NULL;
ALTER TABLE vehicle_tbl ALTER COLUMN engine_cc DROP NOT NULL;
ALTER TABLE vehicle_tbl ALTER COLUMN type DROP NOT NULL;
ALTER TABLE vehicle_tbl ALTER COLUMN seats DROP NOT NULL;
ALTER TABLE vehicle_tbl ALTER COLUMN axles DROP NOT NULL;
ALTER TABLE vehicle_tbl ALTER COLUMN gross_weight DROP NOT NULL;
ALTER TABLE vehicle_tbl ALTER COLUMN max_traction DROP NOT NULL;

INSERT INTO vehicle_tbl (registration_year, car_make, model, location, colour, licence_plate)
VALUES (2013, 'Toyota', 'Corolla XRS 2.0 Flex 16V Aut.', 'Konohagakure', 'Marrom', 'HTY-7065');

INSERT INTO vehicle_tbl (registration_year, car_make, model, location, colour, licence_plate)
VALUES (2014, 'Nissan', 'Sentra SV 2.0 Flex 16V Aut.', 'Sunagakure', 'Branco', 'KWM-0809');

INSERT INTO vehicle_tbl (registration_year, car_make, model, location, colour, licence_plate)
VALUES (1992, 'Hyundai', 'Elantra GLS 1.6', 'Kirigakure', 'Verde', 'NAE-7556');

INSERT INTO vehicle_enquiry_tbl (enquiry_date, license_plate_raw, vehicle_id)
VALUES (now(), 'NAE-7556', (SELECT id FROM vehicle_tbl WHERE model = 'Elantra GLS 1.6'));