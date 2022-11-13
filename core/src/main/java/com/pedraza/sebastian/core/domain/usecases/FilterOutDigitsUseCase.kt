package com.pedraza.sebastian.core.domain.usecases

interface FilterOutDigitsUseCase {
    fun invoke(text: String): String
}