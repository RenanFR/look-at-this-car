package br.com.lookatthiscar.model.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "vehicle_enquiry_tbl")
data class VehicleEnquiry(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "enquiry_date")
    val enquiryDate: LocalDateTime,

    @Column(name = "license_plate_raw")
    var licensePlate: String,

    @ManyToOne
    @JoinColumn(name="vehicle_id", nullable=false)
    var vehicle: VehicleEntity
)
