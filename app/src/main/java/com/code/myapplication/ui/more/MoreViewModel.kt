package com.code.myapplication.ui.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code.myapplication.data.local.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoreViewModel @Inject constructor(val repo: AuthenticationRepository) : ViewModel() {
    private val _state = MutableStateFlow(MoreInfoDataState())
    val state = _state.asStateFlow()


    init {
        getDataInfo()
    }

    fun getDataInfo() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            _state.update {
                it.copy(isLoading = false, moreInfo = repo.getUser())
            }

        }
    }


}