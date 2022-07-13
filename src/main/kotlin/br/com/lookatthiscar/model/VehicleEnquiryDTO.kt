package br.com.lookatthiscar.model

import java.time.LocalDateTime

data class VehicleEnquiryDTO(
    var licensePlate: String,
    val enquiryDate: LocalDateTime,
    val carMake: String,
    val location: String,
    val carModel: String
)