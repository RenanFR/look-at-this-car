package br.com.lookatthiscar

import br.com.lookatthiscar.api.client.PlacaAPIClient
import br.com.lookatthiscar.config.FeignConfig
import br.com.lookatthiscar.config.H2TestConfig
import br.com.lookatthiscar.config.RekognitionConfig
import br.com.lookatthiscar.repository.VehicleEnquiryRepository
import br.com.lookatthiscar.repository.VehicleRepository
import br.com.lookatthiscar.service.LicensePlateService
import br.com.lookatthiscar.service.RekognitionService
import br.com.lookatthiscar.validator.LicensePlateValidator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(
    classes =
    [H2TestConfig::class, LicensePlateService::class, RekognitionConfig::class,
        RekognitionService::class, LicensePlateValidator::class, FeignConfig::class,
        PlacaAPIClient::class, VehicleRepository::class, VehicleEnquiryRepository::class]
)
@ActiveProfiles("test")
@AutoConfigureWireMock(port = 2345, stubs = ["classpath*:/META-INF/**/mappings/**/*.json"])
class LicensePlateServiceIntegrationTest {

    @Autowired
    lateinit var service: LicensePlateService

    @Test
    fun shouldGetCarInformationFromLicensePlate() {
        val inputStream = javaClass.getResource("/green_car.jpeg").openStream()
        val car = service.getCarInformationFromCarBasedOnLicensePlate(inputStream)
        Assertions.assertEquals("J389NLT", car.licensePlate)
        Assertions.assertEquals("FIAT ARGO 1.0 6V Flex.", car.model)
        Assertions.assertEquals(2022, car.year)
        Assertions.assertEquals("Renan Rodrigues", car.currentOwner)
    }

}