package br.com.lookatthiscar

import br.com.lookatthiscar.config.H2TestConfig
import br.com.lookatthiscar.model.entity.VehicleEnquiry
import br.com.lookatthiscar.repository.VehicleEnquiryRepository
import br.com.lookatthiscar.repository.VehicleRepository
import br.com.lookatthiscar.service.LicensePlateService
import br.com.lookatthiscar.service.VehicleEnquiriesService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@SpringBootTest(
    classes =
    [H2TestConfig::class, VehicleEnquiriesService::class]
)
@ActiveProfiles("test")
class VehicleEnquiriesServiceTest {

    @Autowired
    lateinit var service: VehicleEnquiriesService

    @Autowired
    lateinit var vehicleEnquiryRepository: VehicleEnquiryRepository

    @Autowired
    lateinit var vehicleRepository: VehicleRepository

    @Test
    fun shouldGetAllVehicleEnquiries() {
        val vehicleEntity =
            vehicleRepository.save(LicensePlateService.getVehicleRecordFromJson(LicensePlateService.getVehicleWithAllFieldsFromJson(LicensePlateServiceUnitTest.getSampleVehicle())))
        val vehicleEnquiry = VehicleEnquiry(null, LocalDateTime.now(), "RTN8G99", vehicleEntity)
        vehicleEnquiryRepository.save(vehicleEnquiry)
        val vehicleEnquiries = vehicleEnquiryRepository.getAllVehicleEnquiries()
        Assertions.assertNotNull(vehicleEnquiries)
        Assertions.assertEquals("RTN8G99", vehicleEnquiries.get(0).licensePlate)
    }

}