package br.com.lookatthiscar.service

import br.com.lookatthiscar.exception.NoLicencePlateInImageException
import br.com.lookatthiscar.validator.LicensePlateValidator
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
class RekognitionService(
    var rekognitionClient: RekognitionClient,
    var licensePlateValidator: LicensePlateValidator
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun getCarLicencePlateFromImage(sourceImage: InputStream): String {
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
            val licensePlate = textCollection.stream()
                .filter { text -> text.confidence() > 50 }
                .peek { text -> logger.debug("Possivel texto de placa encontrado: ${text.detectedText()} com confiabilidade: ${text.confidence()}") }
                .map { text -> text.detectedText().filter { !it.isWhitespace() } }
                .filter { text -> licensePlateValidator.validate(text) }
                .findFirst()
                .orElseThrow { NoLicencePlateInImageException("Nao foi possivel encontrar uma placa de carro na imagem recebida") }
            logger.debug("Placa detectada: ${licensePlate}")
            return licensePlate

        } catch (rekognitionException: RekognitionException) {
            logger.error("Exception: ${rekognitionException.message}")

        } catch (fileNotFoundException: FileNotFoundException) {
            logger.error("Exception: ${fileNotFoundException.message}")

        }
        throw IllegalArgumentException("A imagem que voce nos passou nao pode ser processada")
    }

}