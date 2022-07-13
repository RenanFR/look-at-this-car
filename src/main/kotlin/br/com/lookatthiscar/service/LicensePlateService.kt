package br.com.lookatthiscar.service

import br.com.lookatthiscar.model.entity.VehicleEnquiry
import br.com.lookatthiscar.api.client.PlacaAPIClient
import br.com.lookatthiscar.model.Car
import br.com.lookatthiscar.model.VehicleJson
import br.com.lookatthiscar.repository.VehicleEnquiryRepository
import br.com.lookatthiscar.repository.VehicleRepository
import br.com.lookatthiscar.repository.mapper.VehicleMapper
import com.fasterxml.jackson.databind.ObjectMapper
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.io.InputStream
import java.time.LocalDateTime


@Service
class LicensePlateService(
    var rekognitionService: RekognitionService,
    var placaApiClient: PlacaAPIClient,
    var vehicleRepository: VehicleRepository,
    var vehicleEnquiryRepository: VehicleEnquiryRepository
) {

    fun getCarInformationFromCarBasedOnLicensePlate(sourceImage: InputStream): Car {
        val objectMapper = ObjectMapper()
        val licencePlateFromImage = rekognitionService.getCarLicencePlateFromImage(sourceImage)
        val vehicle =
            placaApiClient.getBrazilCarInformationFromLicensePlate(licencePlateFromImage)
        val vehicleData = objectMapper.readValue(vehicle.vehicleJson, VehicleJson::class.java)
        convertAndSaveVehicle(vehicleData, licencePlateFromImage)
        return Car(
            vehicleData.description,
            licencePlateFromImage,
            vehicleData.registrationYear,
            "Renan Rodrigues"
        )
    }

    private fun convertAndSaveVehicle(vehicleData: VehicleJson, licensePlateRaw: String) {
        val vehicleMapper = Mappers.getMapper(VehicleMapper::class.java)
        val vehicleEntity = vehicleMapper.entityFromDTO(vehicleData)
        val newVehicleRecord = vehicleRepository.save(vehicleEntity)
        val vehicleEnquiryEntry = VehicleEnquiry(null, LocalDateTime.now(), licensePlateRaw, newVehicleRecord)
        vehicleEnquiryRepository.save(vehicleEnquiryEntry)
    }
}