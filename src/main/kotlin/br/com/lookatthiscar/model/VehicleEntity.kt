package br.com.lookatthiscar.model

import javax.persistence.*

@Entity
@Table(name = "vehicle_tbl")
data class VehicleEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    val description: String,

    @Column(table = "registration_year")
    val registrationYear: Int,

    @Column(table = "car_make")
    val carMake: String,

    @Column(table = "model")
    val carModel: String,

    @Column(table = "image_url")
    val imageUrl: String?,

    val location: String?,
    val vin: String?,
    val fuel: String?,
    val colour: String?,
    val power: String?,

    @Column(table = "engine_cc")
    val engineCc: String,

    val seats: String,
    val axles: String?,

    @Column(table = "gross_weight")
    val grossWeight: String?,

    @Column(table = "max_traction")
    val maxTraction: String?,

    @Column(table = "licence_plate")
    val licencePlate: String?
)