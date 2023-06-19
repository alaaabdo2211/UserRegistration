package com.code.myapplication.domain.usecases

import com.code.myapplication.R
import com.code.myapplication.util.Patterns
import javax.inject.Inject


class ValidateJordanianPhoneNumberUseCase @Inject constructor() {
    operator fun invoke(phoneNumber: String): ValidationModel {
        phoneNumber.trim().let {
            return if (it.isEmpty()) {
                ValidationModel(false, errorMessageResId = R.string.phone_number_is_required)
            } else if (!Patterns.JORDANIAN_PHONE_NUMBER.matcher(it).matches()) {
                ValidationModel(
                    false,
                    errorMessageResId = R.string.please_enter_a_valid_jordanian_phone_number
                )
            } else
                ValidationModel(true)
        }
    }
}