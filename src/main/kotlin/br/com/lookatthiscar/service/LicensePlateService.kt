package br.com.lookatthiscar.service

import br.com.lookatthiscar.api.client.PlacaAPIClient
import br.com.lookatthiscar.model.Car
import br.com.lookatthiscar.model.VehicleJson
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import java.io.InputStream


@Service
class LicensePlateService(var rekognitionService: RekognitionService, var placaApiClient: PlacaAPIClient) {

    fun getCarInformationFromCarBasedOnLicensePlate(sourceImage: InputStream): Car {
        val objectMapper = ObjectMapper()
        val licencePlateFromImage = rekognitionService.getCarLicencePlateFromImage(sourceImage)
        val vehicle =
            placaApiClient.getBrazilCarInformationFromLicensePlate(licencePlateFromImage)
        val vehicleData = objectMapper.readValue(vehicle.vehicleJson, VehicleJson::class.java)
        return Car(
            vehicleData.description,
            licencePlateFromImage,
            vehicleData.registrationYear,
            "Renan Rodrigues"
        )
    }
}