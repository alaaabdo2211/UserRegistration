package com.code.myapplication.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code.myapplication.data.remote.MostViewRepository
import com.code.myapplication.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(val repo: MostViewRepository) : ViewModel() {


    private val _state = MutableStateFlow(MostViewedDataState())
    val state = _state.asStateFlow()

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true, errorMessage = null)
            }
            val listOfData = repo.getMostView()
            when (listOfData) {
                is ApiState.Error -> _state.update {
                    it.copy(isLoading = false, errorMessage = listOfData.message)
                }
                is ApiState.Success -> _state.update {
                    it.copy(
                        isLoading = false, errorMessage = null, mostViewedDataList = listOfData.data
                    )
                }


            }

        }
    }

}