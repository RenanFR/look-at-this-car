package br.com.lookatthiscar

import br.com.lookatthiscar.api.client.PlacaAPIClient
import br.com.lookatthiscar.config.FeignConfig
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@ActiveProfiles("test")
@SpringBootTest(classes = [FeignConfig::class])
@AutoConfigureWireMock(port = 2345, stubs = ["classpath*:/META-INF/**/mappings/**/*.json"])
class PlacaAPIClientTest {

    @Autowired
    lateinit var placaAPIClient: PlacaAPIClient

    @Test
    fun shouldFetchCarDataFromApiByLicensePlate() {
        val response = placaAPIClient.getBrazilCarInformationFromLicensePlate("RTN8G99")
        assertNotNull(response)
        assertNotNull(response.vehicleJson)
        assertEquals("FIAT ARGO 1.0 6V Flex.", response.vehicleData.description)
        assertEquals(2022, response.vehicleData.registrationYear)
    }
}