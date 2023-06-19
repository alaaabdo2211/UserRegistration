package com.code.myapplication.ui

import androidx.lifecycle.ViewModel
import com.code.myapplication.domain.repositories.SettingsRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val settingRepo: SettingsRepositories) :
    ViewModel() {

    fun updateLanguage(language: String) {
        settingRepo.updateLanguage(language)
    }

    fun getLanguage(): String {
        return settingRepo.getLanguage()
    }


}