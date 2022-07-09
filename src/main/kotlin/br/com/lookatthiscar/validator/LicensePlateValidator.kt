package br.com.lookatthiscar.validator

import org.springframework.stereotype.Component

@Component
class LicensePlateValidator {

    fun validate(licensePlate: String): Boolean {
        return getAcceptablePatternForLicensePlate().stream().anyMatch { pattern ->
            val regex: Regex = Regex(pattern, RegexOption.IGNORE_CASE)
            regex.containsMatchIn(licensePlate)
        }
    }

    fun getAcceptablePatternForLicensePlate(): List<String> {
        return listOf("[A-Z]{3}[0-9][0-9A-Z][0-9]{2}", "[A-Z]{1}[0-9]{3}[A-Z]{3}")
    }

}