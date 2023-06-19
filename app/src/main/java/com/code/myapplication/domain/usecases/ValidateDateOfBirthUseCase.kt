package com.code.myapplication.domain.usecases

import com.code.myapplication.R
import javax.inject.Inject

class ValidateDateOfBirthUseCase    @Inject constructor()  {
    operator fun invoke(date: String): ValidationModel {
        return if (date.isBlank()) {
            ValidationModel(isValid = false, errorMessageResId = R.string.date_is_required)
        } else ValidationModel(isValid = true)
    }
}