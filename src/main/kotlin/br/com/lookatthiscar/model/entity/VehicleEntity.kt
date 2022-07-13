package br.com.lookatthiscar.model.entity

import javax.persistence.*

@Entity
@Table(name = "vehicle_tbl")
data class VehicleEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    val description: String,

    @Column(name = "registration_year")
    val registrationYear: Int,

    @Column(name = "car_make")
    val carMake: String,

    @Column(name = "model")
    val carModel: String,

    @Column(name = "image_url")
    val imageUrl: String?,

    val location: String?,
    val vin: String?,
    val fuel: String?,
    val colour: String?,
    val power: String?,

    @Column(name = "engine_cc")
    val engineCc: String,

    val seats: String,
    val axles: String?,

    @Column(name = "gross_weight")
    val grossWeight: String?,

    @Column(name = "max_traction")
    val maxTraction: String?,

    @Column(name = "licence_plate")
    val licencePlate: String?,

    @OneToMany(mappedBy="vehicle")
    val vehicleEnquiries: List<VehicleEnquiry>?

)