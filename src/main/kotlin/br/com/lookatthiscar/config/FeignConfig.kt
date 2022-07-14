package br.com.lookatthiscar.config

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableFeignClients(basePackages = ["br.com.lookatthiscar.api.client"])
@ImportAutoConfiguration(classes = [FeignAutoConfiguration::class, HttpMessageConvertersAutoConfiguration::class])
class FeignConfig {

    @Value("\${placaapi.username}")
    private lateinit var username: String

    @Bean
    fun requestInterceptor(): RequestInterceptor? {
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            requestTemplate.query("username", username)
        }
    }

}