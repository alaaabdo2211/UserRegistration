package com.code.myapplication.domain.usecases

import javax.inject.Inject


data class ValidationUseCase @Inject constructor (
    val validateEmail: ValidateEmailUseCase ,
    val validatePassword: ValidatePasswordUseCase ,
    val validateFullName: ValidateFullNameUseCase,
    val validatePhoneNumber: ValidateJordanianPhoneNumberUseCase,
    val validateDateOfBirth: ValidateDateOfBirthUseCase,
    val validateNationalId : ValidateNationalIDUseCase

)
