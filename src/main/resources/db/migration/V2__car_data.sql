CREATE TABLE vehicle_tbl(
    id SERIAL PRIMARY KEY,
    model VARCHAR(50) NOT NULL,
    car_make VARCHAR(50) NOT NULL,
    licence_plate VARCHAR(50) NOT NULL,
    image_url VARCHAR(68) NOT NULL,
    vin VARCHAR(50) NOT NULL,
    location VARCHAR(50) NOT NULL,
    fuel VARCHAR(50) NOT NULL,
    colour VARCHAR(50) NOT NULL,
    power VARCHAR(50) NOT NULL,
    engine_cc VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL,
    seats SMALLINT NOT NULL,
    axles VARCHAR(50) NOT NULL,
    gross_weight VARCHAR(50) NOT NULL,
    registration_year INT NOT NULL,
    max_traction VARCHAR(50) NOT NULL
);