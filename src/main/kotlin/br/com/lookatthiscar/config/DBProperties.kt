package br.com.lookatthiscar.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "spring.datasource")
@ConstructorBinding
data class DBProperties(

    val username: String,
    val url: String,
    val password: String,
    val driverClassName: String
)