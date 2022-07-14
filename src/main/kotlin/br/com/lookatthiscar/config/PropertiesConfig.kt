package br.com.lookatthiscar.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(DBProperties::class)
class PropertiesConfig {
}