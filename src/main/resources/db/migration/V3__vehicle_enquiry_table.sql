CREATE TABLE vehicle_enquiry_tbl(
    id SERIAL PRIMARY KEY,
    vehicle_id BIGINT NOT NULL,
    license_plate_raw VARCHAR(10) NOT NULL,
    enquiry_date TIME NOT NULL
);

CONSTRAINT fk_vehicle_enquiry FOREIGN KEY(vehicle_id) REFERENCES vehicle_tbl(vehicle_id)