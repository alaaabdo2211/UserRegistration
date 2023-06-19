package com.code.myapplication.domain.usecases

import androidx.annotation.StringRes

data class ValidationModel(
    val isValid: Boolean, @StringRes val errorMessageResId: Int? = null
)