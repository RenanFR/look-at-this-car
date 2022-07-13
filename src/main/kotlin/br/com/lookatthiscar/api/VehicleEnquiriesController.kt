package br.com.lookatthiscar.api

import br.com.lookatthiscar.model.entity.VehicleEnquiry
import br.com.lookatthiscar.repository.VehicleRepository
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/vehicles/enquiries")
class VehicleEnquiriesController(
    var vehicleRepository: VehicleRepository
) {

    @PostMapping(consumes = ["multipart/form-data"])
    fun receiveCarImage(@RequestParam(name = "carImageFile") file: MultipartFile): String {
        return "Hello World!"
    }

    @GetMapping
    fun retrievePastVehicleEnquiries(): List<VehicleEnquiry>? {
        return null
    }

}