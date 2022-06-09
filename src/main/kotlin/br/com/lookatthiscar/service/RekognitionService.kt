package br.com.lookatthiscar.service

import br.com.lookatthiscar.model.Car
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import software.amazon.awssdk.core.SdkBytes
import software.amazon.awssdk.services.rekognition.RekognitionClient
import software.amazon.awssdk.services.rekognition.model.DetectTextRequest
import software.amazon.awssdk.services.rekognition.model.DetectTextResponse
import software.amazon.awssdk.services.rekognition.model.Image
import software.amazon.awssdk.services.rekognition.model.RekognitionException
import java.io.FileNotFoundException
import java.io.InputStream

@Service
class RekognitionService(val rekognitionClient: RekognitionClient) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun getCarLicencePlateFromImage(sourceImage: InputStream): Car {
        try {

            val sourceBytes = SdkBytes.fromInputStream(sourceImage)
            val souImage: Image = Image.builder()
                .bytes(sourceBytes)
                .build()
            val textRequest = DetectTextRequest.builder()
                .image(souImage)
                .build()
            val textResponse: DetectTextResponse = rekognitionClient.detectText(textRequest)
            val textCollection = textResponse.textDetections()
            for (text in textCollection) {
                logger.debug("Detected: ${text.detectedText()}")
                logger.debug("Confidence: ${text.confidence().toString()}")
                logger.debug("Id: ${text.id()}")
                logger.debug("Parent Id: ${text.parentId()}")
                logger.debug("Type: ${text.type()}")
            }
        } catch (rekognitionException: RekognitionException) {
                logger.error("Exception: ${rekognitionException.message}")

        } catch (fileNotFoundException: FileNotFoundException) {
                logger.error("Exception: ${fileNotFoundException.message}")

        }
        return Car("Creta", "TBD", 2022, "Renan Rodrigues")
    }

}