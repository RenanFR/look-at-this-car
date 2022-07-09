package br.com.lookatthiscar.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class VehicleData(
    @JacksonXmlProperty(localName = "CarMake")
    val carMake: CarMake,
    @JacksonXmlProperty(localName = "CarModel")
    val carModel: String,
    @JacksonXmlProperty(localName = "Description")
    val description: String,
    @JacksonXmlProperty(localName = "EngineSize")
    val engineSize: EngineSize,
    @JacksonXmlProperty(localName = "NumberOfSeats")
    val numberOfSeats: NumberOfSeats,
    @JacksonXmlProperty(localName = "RegistrationYear")
    val registrationYear: Int,
    @JacksonXmlProperty(localName = "FuelType")
    val fuelType: FuelType
)
