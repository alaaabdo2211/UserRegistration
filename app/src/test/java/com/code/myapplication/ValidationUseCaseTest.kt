package com.code.myapplication

import com.code.myapplication.domain.usecases.*
import com.google.common.truth.Truth
import org.junit.Test


internal class ValidationUseCaseTest {
    val validateEmailUseCase = ValidateEmailUseCase()
    val validatePasswordUseCase = ValidatePasswordUseCase()
    val validatePhoneNumberUseCase = ValidateJordanianPhoneNumberUseCase()
    val validateNationalIdUseCase = ValidateNationalIDUseCase()
    val validateDateOfBirthUseCase = ValidateDateOfBirthUseCase()
    val validateFullNameUseCase = ValidateFullNameUseCase()





    @Test
    fun `empty email should return is valid false`() {
        val useCase = validateEmailUseCase("")
        Truth.assertThat(useCase.isValid).isEqualTo(false)
    }

    @Test
    fun `invalid email format should return false`() {
        val useCase = validateEmailUseCase("awaqowie.o")
        Truth.assertThat(useCase.isValid).isEqualTo(false)
    }

    @Test
    fun `valid email format should return true`() {
        val useCase = validateEmailUseCase("a@yahoo.com")
        Truth.assertThat(useCase.isValid).isEqualTo(true)
    }

    @Test
    fun `invalid password format should return false`() {
        val useCase = validatePasswordUseCase("a12345")
        Truth.assertThat(useCase.isValid).isEqualTo(false)
    }
    @Test
    fun `empty password should return false`() {
        val useCase = validatePasswordUseCase("")
        Truth.assertThat(useCase.isValid).isEqualTo(false)
    }

    @Test
    fun `valid password format should return true`() {
        val useCase = validatePasswordUseCase("A1234567a@")
        Truth.assertThat(useCase.isValid).isEqualTo(true)
    }

    @Test
    fun `empty nationalId should return false`() {
        val useCase = validateNationalIdUseCase("")
        Truth.assertThat(useCase.isValid).isEqualTo(false)
    }
    @Test
    fun `invalid nationalId format should return false`() {
        val useCase = validateNationalIdUseCase("a12332b")
        Truth.assertThat(useCase.isValid).isEqualTo(false)
    }
    @Test
    fun `valid nationalId format should return true`() {
        val useCase = validateNationalIdUseCase("1234567")
        Truth.assertThat(useCase.isValid).isEqualTo(true)
    }

    @Test
    fun `empty dateOfBirth should return false`() {
        val useCase = validateDateOfBirthUseCase("")
        Truth.assertThat(useCase.isValid).isEqualTo(false)
    }
    @Test
    fun `valid dateOfBirth should return true`() {
        val useCase = validateDateOfBirthUseCase("12-07-1997")
        Truth.assertThat(useCase.isValid).isEqualTo(true)
    }

    @Test
    fun `empty phoneNumber should return false`() {
        val useCase = validatePhoneNumberUseCase("")
        Truth.assertThat(useCase.isValid).isEqualTo(false)
    }

    @Test
    fun `invalid phoneNumber format should return false`() {
        val useCase = validatePhoneNumberUseCase("9084993092")
        Truth.assertThat(useCase.isValid).isEqualTo(false)
    }

    @Test
    fun `valid phoneNumber format should return true`() {
        val useCase = validatePhoneNumberUseCase("0798989897")
        Truth.assertThat(useCase.isValid).isEqualTo(true)
    }

    @Test
    fun `empty fullName should return false`() {
        val useCase = validateFullNameUseCase("")
        Truth.assertThat(useCase.isValid).isEqualTo(false)
    }

    @Test
    fun `not empty fullName should return true`() {
        val useCase = validateFullNameUseCase("Alaa Abdo")
        Truth.assertThat(useCase.isValid).isEqualTo(true)
    }

}