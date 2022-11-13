package com.pedraza.sebastian.core.domain.usecases

class FilterOutDigitsUseCaseImpl : FilterOutDigitsUseCase {
    override fun invoke(text: String): String = text.filter { it.isDigit() }
}