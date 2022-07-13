package br.com.lookatthiscar.service

import br.com.lookatthiscar.model.entity.VehicleEnquiry
import br.com.lookatthiscar.repository.VehicleEnquiryRepository
import org.springframework.stereotype.Service

@Service
class VehicleEnquiriesService(
    var vehicleEnquiryRepository: VehicleEnquiryRepository
) {
    fun getAllVehicleEnquiries(): List<VehicleEnquiry> {
        return vehicleEnquiryRepository.findAll()
    }
}