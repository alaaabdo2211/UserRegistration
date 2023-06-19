package com.code.myapplication.domain.usecases

import com.code.myapplication.R
import com.code.myapplication.util.Patterns
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {
    operator fun invoke(password: String): ValidationModel {
        return if (password.isBlank()) {
            ValidationModel(false, errorMessageResId = R.string.password_is_required)
        } else if (!Patterns.PASSWORD_PATTERN.matcher(password).matches()) {
            ValidationModel(false, errorMessageResId = R.string.password_validation)
        } else ValidationModel(true)
    }
}