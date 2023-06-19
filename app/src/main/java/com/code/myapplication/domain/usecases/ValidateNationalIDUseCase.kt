package com.code.myapplication.domain.usecases

import androidx.core.text.isDigitsOnly
import com.code.myapplication.R
import javax.inject.Inject

class ValidateNationalIDUseCase @Inject constructor() {
    operator fun invoke(nationalID: String): ValidationModel {
        nationalID.trim().let {
            return if (it.isEmpty()) {
                ValidationModel(
                    isValid = false,
                    errorMessageResId = R.string.national_id_is_required
                )
            } else if (!it.isDigitsOnly()) {
                ValidationModel(
                    isValid = false,
                    errorMessageResId = R.string.enter_a_valid_national_id
                )
            } else ValidationModel(true)
        }
    }
}