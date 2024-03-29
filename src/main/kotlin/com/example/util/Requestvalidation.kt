package com.example.util

import com.example.features.auth.SignUpRequest
import io.ktor.server.plugins.requestvalidation.RequestValidationConfig
import io.ktor.server.plugins.requestvalidation.ValidationResult

fun RequestValidationConfig.authValidation() {
    validate<SignUpRequest> { request ->
        validateObject(request, listOf({ request.name.isNotBlank() } to "Model must not be empty"))
    }
}


fun <T> validateObject(obj: T, validations: List<Pair<() -> Boolean, String>>): ValidationResult {
    for ((condition, errorMessage) in validations) {
        if (obj?.equals(condition) == false) {
            return ValidationResult.Invalid(errorMessage)
        }
    }
    return ValidationResult.Valid
}