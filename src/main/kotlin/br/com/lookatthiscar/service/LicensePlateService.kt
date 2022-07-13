package br.com.lookatthiscar.service

import br.com.lookatthiscar.api.client.PlacaAPIClient
import br.com.lookatthiscar.model.Car
import br.com.lookatthiscar.model.Vehicle
import br.com.lookatthiscar.model.VehicleJson
import br.com.lookatthiscar.model.entity.VehicleEnquiry
import br.com.lookatthiscar.model.entity.VehicleEntity
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
        val licencePlateFromImage = rekognitionService.getCarLicencePlateFromImage(sourceImage)
        val vehicle =
            placaApiClient.getBrazilCarInformationFromLicensePlate(licencePlateFromImage)
        val vehicleData = getVehicleWithAllFieldsFromJson(vehicle)
        convertAndSaveVehicle(vehicleData, licencePlateFromImage)
        return Car(
            vehicleData.description,
            licencePlateFromImage,
            vehicleData.registrationYear,
            "Renan Rodrigues"
        )
    }

    companion object {

        fun getVehicleWithAllFieldsFromJson(
            vehicle: Vehicle
        ): VehicleJson {
            val objectMapper = ObjectMapper()
            val vehicleData = objectMapper.readValue(vehicle.vehicleJson, VehicleJson::class.java)
            return vehicleData
        }
        fun getVehicleRecordFromJson(
            vehicleData: VehicleJson
        ): VehicleEntity {
            val vehicleMapper = Mappers.getMapper(VehicleMapper::class.java)
            return vehicleMapper.entityFromDTO(vehicleData)
        }
    }

    private fun convertAndSaveVehicle(vehicleData: VehicleJson, licensePlateRaw: String) {
        val vehicleEntity = getVehicleRecordFromJson(vehicleData)
        val newVehicleRecord = vehicleRepository.save(vehicleEntity)
        val vehicleEnquiryEntry = VehicleEnquiry(null, LocalDateTime.now(), licensePlateRaw, newVehicleRecord)
        vehicleEnquiryRepository.save(vehicleEnquiryEntry)
    }
}