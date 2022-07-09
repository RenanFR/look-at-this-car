package br.com.lookatthiscar.model

import com.fasterxml.jackson.annotation.JsonProperty

data class VehicleJson(
    @JsonProperty("Description")
    val description: String,
    @JsonProperty("RegistrationYear")
    val registrationYear: Int,
    @JsonProperty("CarMake")
    val carMake: CarMake,
    @JsonProperty("CarModel")
    val carModel: CarMake,
    @JsonProperty("MakeDescription")
    val makeDescription: CarMake,
    @JsonProperty("ModelDescription")
    val modelDescription: CarMake,
    @JsonProperty("ImageUrl")
    val imageUrl: String,
    @JsonProperty("Location")
    val location: String,
    @JsonProperty("Vin")
    val vin: String,
    @JsonProperty("Fuel")
    val fuel: String,
    @JsonProperty("Colour")
    val colour: String,
    @JsonProperty("Power")
    val power: String,
    @JsonProperty("EngineCC")
    val engineCc: String,
    @JsonProperty("Type")
    val type: String,
    @JsonProperty("Seats")
    val seats: String,
    @JsonProperty("Axles")
    val axles: String,
    @JsonProperty("GrossWeight")
    val grossWeight: String,
    @JsonProperty("MaxTraction")
    val maxTraction: String
)
