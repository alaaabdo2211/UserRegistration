package com.code.myapplication.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code.myapplication.data.local.AuthenticationRepository
import com.code.myapplication.data.local.UserEntity
import com.code.myapplication.domain.usecases.ValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val repo: AuthenticationRepository, private val useCase: ValidationUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun submitData(
        fullName: String,
        email: String,
        nationalId: String,
        phoneNumber: String,
        dateOfBirth: String,
        password: String
    ) {

        _state.update {
            it.copy(
                fullNameError = null,
                nationalIdError = null,
                emailError = null,
                passwordError = null,
                phoneNumberError = null,
                dateOfBirthError = null
            )
        }

        _state.value.run {
            val fullNameError = useCase.validateFullName(fullName)
            val emailError = useCase.validateEmail(email)
            val phoneNumberError = useCase.validatePhoneNumber(phoneNumber)
            val passwordError = useCase.validatePassword(password)
            val dateOfBirthError = useCase.validateDateOfBirth(dateOfBirth)
            val nationalIdError = useCase.validateNationalId(nationalId)


            val hasError = listOf(
                fullNameError,
                emailError,
                nationalIdError,
                passwordError,
                phoneNumberError,
                dateOfBirthError
            ).any { !it.isValid }

            if (hasError) {
                _state.update {
                    it.copy(
                        fullNameError = fullNameError.errorMessageResId,
                        emailError = emailError.errorMessageResId,
                        passwordError = passwordError.errorMessageResId,
                        nationalIdError = nationalIdError.errorMessageResId,
                        phoneNumberError = phoneNumberError.errorMessageResId,
                        dateOfBirthError = dateOfBirthError.errorMessageResId
                    )
                }
            } else {
                registerUser(fullName, email, nationalId, phoneNumber, dateOfBirth, password)
            }
        }
    }

    fun getCurrentTime(): String = try {

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val date = Date(System.currentTimeMillis())

        sdf.format(date)

    } catch (e: Exception) {
        ""
    }

    private fun registerUser(
        fullName: String,
        email: String,
        nationalId: String,
        phoneNumber: String,
        dateOfBirth: String,
        password: String
    ) {
        viewModelScope.launch {
            repo.insert(
                UserEntity(
                    fullName = fullName,
                    email = email,
                    nationalId = nationalId,
                    phoneNumber = phoneNumber,
                    dateOfBirth = dateOfBirth,
                    password = password,
                    dateOfRegistration = getCurrentTime()
                )
            )
        }
    }

    fun checkIfExist(email: String): Boolean {
        var isTaken = false
        viewModelScope.launch {
            isTaken = repo.isTaken(email)
        }
        return isTaken
    }


}