package br.com.lookatthiscar

import br.com.lookatthiscar.api.client.PlacaAPIClient
import br.com.lookatthiscar.model.*
import br.com.lookatthiscar.service.LicensePlateService
import br.com.lookatthiscar.service.RekognitionService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LicensePlateServiceTest {

    val rekognitionService: RekognitionService = mockk()
    val placaAPIClient: PlacaAPIClient = mockk()

    val licensePlateService = LicensePlateService(rekognitionService, placaAPIClient);

    @Test
    fun shouldGetCarInformationFromLicensePlate() {
        val inputStream = javaClass.getResource("/green_car.jpeg").openStream()
        every { rekognitionService.getCarLicencePlateFromImage(inputStream) } returns "RTN8G99"
        every {
            placaAPIClient.getBrazilCarInformationFromLicensePlate(
                "RTN8G99"
            )
        } returns
            Vehicle(
                "{ \"Description\": \"FIAT ARGO 1.0 6V Flex.\", \"RegistrationYear\": \"2022\", \"CarMake\": { \"CurrentTextValue\": \"FIAT\" }, \"CarModel\": { \"CurrentTextValue\": \"ARGO 1.0 6V Flex.\" }, \"MakeDescription\": { \"CurrentTextValue\": \"FIAT\" }, \"ModelDescription\": { \"CurrentTextValue\": \"ARGO 1.0 6V Flex.\" }, \"ImageUrl\": \"http://www.placaapi.com/image.aspx/@RklBVCBBUkdPIDEuMCA2ViBGbGV4Lg==\", \"Location\": \"BELO HORIZONTE, MG\", \"Vin\": \"9BD358A1NNYL71058\", \"Fuel\": \"flex\", \"Colour\": \"PRATA\", \"Power\": \"077\", \"EngineCC\": \"0999\", \"Type\": \"PASSAGEIRO\", \"Seats\": \"5\", \"Axles\": \"02\", \"GrossWeight\": \"00147\", \"MaxTraction\": \"00187\" }",
                VehicleData(
                    CarMake("FIAT ARGO 1.0 6V Flex."),
                    "2022",
                    "FIAT",
                    EngineSize("0999"),
                    NumberOfSeats("5"),
                    2022,
                    FuelType("flex")
                )
            )
        val car =
            licensePlateService.getCarInformationFromCarBasedOnLicensePlate(inputStream)
        assertEquals("RTN8G99", car.licensePlate)
        assertEquals("FIAT ARGO 1.0 6V Flex.", car.model)
        assertEquals(2022, car.year)
        assertEquals("Renan Rodrigues", car.currentOwner)
    }

}