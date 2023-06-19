package com.code.myapplication.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code.myapplication.R
import com.code.myapplication.data.local.AuthenticationRepository
import com.code.myapplication.util.UiSingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: AuthenticationRepository) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiSingleEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


    fun loginUser(email: String, password: String) {

        viewModelScope.launch {
            if (repo.login(email, password)) {
                _uiEvent.emit(UiSingleEvent.NavigateToNextPage)
            } else {
                _uiEvent.emit(UiSingleEvent.ShowSnackBar(R.string.check_email_or_password))
            }
        }

    }


}