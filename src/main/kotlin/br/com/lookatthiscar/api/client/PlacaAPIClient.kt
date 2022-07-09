package br.com.lookatthiscar.api.client

import br.com.lookatthiscar.model.Vehicle
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "placaapi", url = "\${placaapi.url:http://localhost:2345}")
interface PlacaAPIClient {

    @RequestMapping(
        path = ["/api/reg.asmx/CheckBrazil"],
        method = [RequestMethod.GET],
        consumes = [MediaType.APPLICATION_XML_VALUE]
    )
    fun getBrazilCarInformationFromLicensePlate(
        @RequestParam("RegistrationNumber") registrationNumber: String
    ): Vehicle
}