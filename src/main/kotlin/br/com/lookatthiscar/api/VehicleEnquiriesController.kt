package br.com.lookatthiscar.api

import br.com.lookatthiscar.model.VehicleEnquiryDTO
import br.com.lookatthiscar.service.VehicleEnquiriesService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vehicles/enquiries")
class VehicleEnquiriesController(
    var service: VehicleEnquiriesService
) {

    @GetMapping
    fun retrievePastVehicleEnquiries(): ResponseEntity<List<VehicleEnquiryDTO>>? {
        return ResponseEntity.ok(service.getAllVehicleEnquiries())
    }

}