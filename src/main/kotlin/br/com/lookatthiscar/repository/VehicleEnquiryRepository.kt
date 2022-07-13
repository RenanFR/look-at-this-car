package br.com.lookatthiscar.repository

import br.com.lookatthiscar.model.VehicleEnquiryDTO
import br.com.lookatthiscar.model.entity.VehicleEnquiry
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface VehicleEnquiryRepository : JpaRepository<VehicleEnquiry, Int> {

    @Query("SELECT new br.com.lookatthiscar.model.VehicleEnquiryDTO(vehicleEnquiry.licensePlate, vehicleEnquiry.enquiryDate, vehicle.carMake, vehicle.location, vehicle.carModel) FROM VehicleEnquiry vehicleEnquiry JOIN vehicleEnquiry.vehicle vehicle")
    fun getAllVehicleEnquiries(): List<VehicleEnquiryDTO>

}