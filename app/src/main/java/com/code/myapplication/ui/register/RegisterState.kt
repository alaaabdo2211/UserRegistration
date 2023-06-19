package com.code.myapplication.ui.register

import androidx.annotation.StringRes


data class RegisterState(
    @StringRes val fullNameError: Int? = null,
    @StringRes val emailError: Int? = null,
    @StringRes val nationalIdError: Int? = null,
    @StringRes val phoneNumberError: Int? = null,
    @StringRes val dateOfBirthError: Int? = null,
    @StringRes val passwordError: Int? = null,
)