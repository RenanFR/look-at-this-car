package br.com.lookatthiscar

import br.com.lookatthiscar.config.RekognitionConfig
import br.com.lookatthiscar.service.RekognitionService
import org.junit.Assert.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = arrayOf(RekognitionConfig::class, RekognitionService::class))
class RekognitionServiceTest {

    @Autowired
    lateinit var rekognitionService: RekognitionService

    @Test
    fun shouldGetLicensePlateFromImage() {
        val inputStream = javaClass.getResource("/green_car.jpeg").openStream()

        val car = rekognitionService.getCarLicencePlateFromImage(inputStream);

        assertNotNull(car)
    }
}