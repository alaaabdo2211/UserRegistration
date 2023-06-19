package com.code.myapplication.domain.usecases

import androidx.core.text.isDigitsOnly
import com.code.myapplication.R
import com.code.myapplication.util.Patterns
import javax.inject.Inject

class ValidateNationalIDUseCase @Inject constructor() {
    operator fun invoke(nationalID: String): ValidationModel {
        nationalID.trim().let {
            return if (it.isEmpty()) {
                ValidationModel(
                    isValid = false,
                    errorMessageResId = R.string.national_id_is_required
                )
            } else if (!Patterns.NATIONAL_ID_PATTERN.matcher(it).matches()) {
                ValidationModel(
                    isValid = false,
                    errorMessageResId = R.string.enter_a_valid_national_id
                )
            } else ValidationModel(true)
        }
    }
}