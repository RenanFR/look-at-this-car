package br.com.lookatthiscar.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class EngineSize(
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "CurrentValue")
    val CurrentValue: String
)
