package br.com.lookatthiscar

import br.com.lookatthiscar.config.RekognitionConfig
import br.com.lookatthiscar.service.RekognitionService
import br.com.lookatthiscar.validator.LicensePlateValidator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest(classes = [RekognitionConfig::class, RekognitionService::class, LicensePlateValidator::class])
@ExtendWith(SpringExtension::class)
class RekognitionServiceTest {

    @Autowired
    lateinit var rekognitionService: RekognitionService

    @Test
    fun shouldGetLicensePlateFromImage() {
        val inputStream = javaClass.getResource("/green_car.jpeg").openStream()
        val licensePlateText = rekognitionService.getCarLicencePlateFromImage(inputStream);
        assertNotNull(licensePlateText)
        assertEquals("J389NLT", licensePlateText)
    }
}