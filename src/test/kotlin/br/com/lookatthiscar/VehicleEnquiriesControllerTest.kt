package br.com.lookatthiscar

import br.com.lookatthiscar.api.VehicleEnquiriesController
import br.com.lookatthiscar.service.VehicleEnquiriesService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(
    VehicleEnquiriesController::class
)
class VehicleEnquiriesControllerTest {

    @Autowired
    private val mockMvc: MockMvc? = null

    @MockBean
    lateinit var service: VehicleEnquiriesService

    @Test
    @Throws(Exception::class)
    fun shouldGetVehicleEnquiriesResponse() {
        `when`(service.getAllVehicleEnquiries()).thenReturn(listOf())
        mockMvc!!.perform(MockMvcRequestBuilders.get("/vehicles/enquiries")).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
    }

}