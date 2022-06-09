package br.com.lookatthiscar.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/cars")
class CarImageController {

    @PostMapping(
        path = ["/image"], consumes = ["multipart/form-data"]
    )
    fun receiveCarImage(@RequestParam(name = "carImageFile") file: MultipartFile): String {
        return "Hello World!"
    }

}