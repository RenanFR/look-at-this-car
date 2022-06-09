package br.com.lookatthiscar.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.rekognition.RekognitionClient


@Configuration
class RekognitionConfig(
    @Value("\${AWS_REGION}") val awsRegion: String
) {

    @Bean
    fun rekognitionClient(): RekognitionClient {
        val rekClient = RekognitionClient.builder()
            .region(Region.of(awsRegion))
            .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
            .build()
        return rekClient
    }
}