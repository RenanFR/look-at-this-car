package br.com.lookatthiscar.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class FuelType(
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "CurrentValue")
    val currentValue: String
)
