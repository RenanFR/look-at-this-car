package br.com.lookatthiscar.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class CarMake(
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "CurrentTextValue")
    @JsonProperty("CurrentTextValue")
    val currentTextValue: String
)
