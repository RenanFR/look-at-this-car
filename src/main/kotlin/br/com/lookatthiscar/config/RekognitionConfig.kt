package br.com.lookatthiscar.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.rekognition.RekognitionClient


@Configuration
class RekognitionConfig {

    @Bean
    fun rekognitionClient(): RekognitionClient {
        val rekClient = RekognitionClient.builder()
            .region(Region.of("us-east-1"))
            .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
            .build()
        return rekClient
    }
}