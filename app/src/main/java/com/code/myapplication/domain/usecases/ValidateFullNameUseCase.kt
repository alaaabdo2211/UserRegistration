package com.code.myapplication.domain.usecases

import com.code.myapplication.R
import com.code.myapplication.util.Patterns
import javax.inject.Inject

class ValidateFullNameUseCase @Inject constructor() {
    operator fun invoke(fullName: String): ValidationModel {
        fullName.trim().let {
            return if (it.isEmpty()) {
                ValidationModel(isValid = false, errorMessageResId = R.string.full_name_is_required)
            } else if (!Patterns.ONLY_ENGLISH_LETTER_PATTERN_WITH_SPACE.matcher(it).matches()) {
                ValidationModel(
                    false, errorMessageResId = R.string.this_field_only_english_letters
                )
            } else ValidationModel(true)
        }
    }
}