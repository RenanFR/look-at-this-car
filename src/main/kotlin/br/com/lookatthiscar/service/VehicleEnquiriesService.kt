package br.com.lookatthiscar.service

import br.com.lookatthiscar.model.VehicleEnquiryDTO
import br.com.lookatthiscar.repository.VehicleEnquiryRepository
import org.springframework.stereotype.Service

@Service
class VehicleEnquiriesService(
    var vehicleEnquiryRepository: VehicleEnquiryRepository
) {
    fun getAllVehicleEnquiries(): List<VehicleEnquiryDTO> {
        return vehicleEnquiryRepository.getAllVehicleEnquiries()
    }
}